18
https://raw.githubusercontent.com/WeBankFinTech/Schedulis/master/azkaban-web-server/src/main/java/azkaban/webapp/servlet/LoginAbstractAzkabanServlet.java
/*
 * Copyright 2012 LinkedIn Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package azkaban.webapp.servlet;

import static azkaban.ServiceProvider.SERVICE_PROVIDER;

import azkaban.project.Project;
import azkaban.project.ProjectManager;
import azkaban.server.AzkabanServer;
import azkaban.server.session.Session;
import azkaban.user.Permission;
import azkaban.user.Role;
import azkaban.user.User;
import azkaban.user.UserManager;
import azkaban.user.UserManagerException;
import azkaban.utils.Props;
import azkaban.utils.StringUtils;
import azkaban.utils.WebUtils;
import azkaban.webapp.AzkabanWebServer;
import azkaban.webapp.WebMetrics;
import com.webank.wedatasphere.dss.appjoint.auth.AppJointAuth;
import com.webank.wedatasphere.dss.appjoint.auth.RedirectMsg;
import com.webank.wedatasphere.schedulis.common.i18nutils.LoadJsonUtils;
import com.webank.wedatasphere.schedulis.common.system.SystemManager;
import com.webank.wedatasphere.schedulis.common.user.SystemUserManager;
import com.webank.wedatasphere.schedulis.common.utils.XSSFilterUtils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.log4j.Logger;

/**
 * Abstract Servlet that handles auto login when the session hasn't been verified.
 */
public abstract class LoginAbstractAzkabanServlet extends AbstractAzkabanServlet {

  private static final long serialVersionUID = 1L;

  private static final Logger logger = Logger.getLogger(LoginAbstractAzkabanServlet.class.getName());
  private static final String SESSION_ID_NAME = "azkaban.browser.session.id";

  private static final String WTSS_SUPER_USER = "dws-wtss";
  private static final String WTSS_SUPER_USER_PWD = "WeBankBDPWTSS&DWS@2019";

  private static final String WTSS_SUPER_USER_2 = "superadmin";
  private static final String WTSS_SUPER_USER_PWD_2 = "Abcd1234";

  private static final String WTSS_SUPER_USER_STR = "superUser";
  private static final String WTSS_SUPER_USER_PWD_STR = "superUserPwd";

  private static final int DEFAULT_UPLOAD_DISK_SPOOL_SIZE = 20 * 1024 * 1024;

  private static final HashMap<String, String> contextType = new HashMap<>();

  private AzkabanServer application;

  static {
    contextType.put(".js", "application/javascript");
    contextType.put(".css", "text/css");
    contextType.put(".png", "image/png");
    contextType.put(".jpeg", "image/jpeg");
    contextType.put(".gif", "image/gif");
    contextType.put(".jpg", "image/jpeg");
    contextType.put(".eot", "application/vnd.ms-fontobject");
    contextType.put(".svg", "image/svg+xml");
    contextType.put(".ttf", "application/octet-stream");
    contextType.put(".woff", "application/x-font-woff");
  }

  private final WebMetrics webMetrics = SERVICE_PROVIDER.getInstance(WebMetrics.class);
  private File webResourceDirectory = null;
  private MultipartParser multipartParser;
  private boolean shouldLogRawUserAgent = false;

  @Override
  public void init(final ServletConfig config) throws ServletException {
    super.init(config);

    this.multipartParser = new MultipartParser(DEFAULT_UPLOAD_DISK_SPOOL_SIZE);

    this.shouldLogRawUserAgent = getApplication().getServerProps().getBoolean("accesslog.raw.useragent",false);

    //获取Web Server实体对象
    this.application = SERVICE_PROVIDER.getInstance(AzkabanWebServer.class);
  }

  public void setResourceDirectory(final File file) {
    this.webResourceDirectory = file;
  }

  @Override
  protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
          throws ServletException, IOException {
    final Props props = this.application.getServerProps();
    String nginxSSL = props.getString("nginx.ssl.module", "");
    if("open".equals(nginxSSL)) {
      String referer = req.getHeader("Referer");
      String refererUrl = props.getString("azkaban.header.referer.url", "");
      // 判断 Referer 是否以 bank.example 开头
      if ((null != referer) && !(referer.trim().startsWith(refererUrl))) {
        resp.sendRedirect("/error");
      }
    }
    this.webMetrics.markWebGetCall();
    // Set session id
    final Session session = getSessionFromRequest(req);
    logRequest(req, session);
    if (hasParam(req, "logout")) {
      resp.sendRedirect(req.getContextPath());
      if (session != null) {
        getApplication().getSessionCache().removeSession(session.getSessionId());
      }
      return;
    }

    if("/api/v1/redirect".equals(req.getRequestURI())){
      logger.info("ingore the auth of DSS request: {}" +  req.getRequestURI());
      handleDssRequest(req,resp,session);
    }

      //session不为空，或者请求不需要检查session
    if (session != null || isRequestWithoutSession(req)) {
      if("open".equals(nginxSSL)) {
        //XSS参数过滤
        String reqString = req.getQueryString();
        if (XSSFilterUtils.invalidStringFilter(reqString)) {
          resp.sendRedirect("/error");
          return;
        }
        if (XSSFilterUtils.invalidCookieFilter(req)) {
          resp.sendRedirect("/error");
          return;
        }
      }
      if (logger.isDebugEnabled() && session != null) {
        logger.debug("Found session " + session.getUser());
      }
      if (handleFileGet(req, resp)) {
        return;
      }
      if ("/error".equals(req.getRequestURI())) {
        handleErrorRequest(req, resp, "Illegal Request.");
        return;
      }
      // 针对非页面操作的客户端,新增用户直接使用超级管理员
      boolean flag = featureAddSystemUserViaFastTrack(req, resp, "GET");
      // 如果flag是true, 说明未请求 addSystemUserViaFastTrack 接口,此处设置 flag 避免重复添加数据导致报错
      if (flag) {
        handleGet(req, resp, session);
      }
    } else {
      if (hasParam(req, "ajax")) {
        final HashMap<String, String> retVal = new HashMap<>();
        retVal.put("error", "session");
        this.writeJSON(resp, retVal);
      } else if ("/toL".equals(req.getRequestURI())){
        handleLogin(req, resp);
      } else if ("/error".equals(req.getRequestURI())) {
        handleErrorRequest(req, resp, "Illegal Request.");
      } else {
        handleLogin(req, resp);
      }
    }
  }

  private boolean isRequestWithoutSession(HttpServletRequest req) {
    String ajaxName = getParam(req,"ajax", "");
    return ajaxName.equals("executeFlowCycleFromExecutor");
  }

  /**
   * 处理非页面登录的新增用户请求
   * @param req
   * @param resp
   * @param type 请求类型
   * @throws ServletException
   * @throws IOException
   */
  private boolean featureAddSystemUserViaFastTrack(final HttpServletRequest req, final HttpServletResponse resp,
                                                   String type) throws ServletException, IOException {
    boolean flag = true;
    // 针对非页面操作的客户端,新增用户直接使用超级管理员
    if (hasParam(req, "ajax")) {
      String ajaxName = getParam(req, "ajax");
      if (org.apache.commons.lang.StringUtils.isNotBlank(ajaxName)) {
        if (ajaxName.equals("addSystemUserViaFastTrack")) {
          try {
            final String ip = getRealClientIpAddr(req);
            //如果超级用户用户名和密码都是对的，那么我们直接放行, 创建超级管理员的session
            Session session = createSession(WTSS_SUPER_USER_2, WTSS_SUPER_USER_PWD_2, ip, WTSS_SUPER_USER_STR);
            // 使用超级管理员身份处理
            if (type.equals("GET")) {
              handleGet(req, resp, session);
            } else {
              handlePost(req, resp, session);
            }

            // 调用了addSystemUserViaFastTrack接口之后,需要将标识变为false
          } catch (UserManagerException e) {
            logger.error("addSystemUserViaFastTrack exception,cause is:{} ", e);
          }
          // 不管执行结果怎样,本次请求都算是执行过,所以讲flag标识设置为false
          flag = false;
        }
      }
    }
    return flag;
  }

  /**
   * Log out request - the format should be close to Apache access log format
   */
  private void logRequest(final HttpServletRequest req, final Session session) {
    final StringBuilder buf = new StringBuilder();
    buf.append(getRealClientIpAddr(req)).append(" ");
    if (session != null && session.getUser() != null) {
      buf.append(session.getUser().getUserId()).append(" ");
    } else {
      buf.append(" - ").append(" ");
    }

    buf.append("\"");
    buf.append(req.getMethod()).append(" ");
    buf.append(req.getRequestURI()).append(" ");
    if (req.getQueryString() != null && !isIllegalPostRequest(req)) {
      buf.append(req.getQueryString()).append(" ");
    } else {
      buf.append("-").append(" ");
    }
    buf.append(req.getProtocol()).append("\" ");

    final String userAgent = req.getHeader("User-Agent");
    if (this.shouldLogRawUserAgent) {
      buf.append(userAgent);
    } else {
      // simply log a short string to indicate browser or not
      if (StringUtils.isFromBrowser(userAgent)) {
        buf.append("browser");
      } else {
        buf.append("not-browser");
      }
    }

    logger.info(buf.toString());
  }

  private boolean handleFileGet(final HttpServletRequest req, final HttpServletResponse resp)
          throws IOException {
    if (this.webResourceDirectory == null) {
      return false;
    }

    // Check if it's a resource
    final String prefix = req.getContextPath() + req.getServletPath();
    String path = req.getRequestURI().substring(prefix.length());

    // 路径操作漏洞,将非法请求路径中的目录转换为不可到达
    path = path.replace("../", "");

    final int index = path.lastIndexOf('.');
    if (index == -1) {
      return false;
    }

    final String extension = path.substring(index);
    if (contextType.containsKey(extension)) {
      final File file = new File(this.webResourceDirectory, path);
      if (!file.exists() || !file.isFile()) {
        return false;
      }

      resp.setContentType(contextType.get(extension));

      final OutputStream output = resp.getOutputStream();
      BufferedInputStream input = null;
      try {
        input = new BufferedInputStream(new FileInputStream(file));
        IOUtils.copy(input, output);
      } finally {
        if (input != null) {
          input.close();
        }
      }
      output.flush();
      return true;
    }

    return false;
  }

  private String getRealClientIpAddr(final HttpServletRequest req) {

    // If some upstream device added an X-Forwarded-For header
    // use it for the client ip
    // This will support scenarios where load balancers or gateways
    // front the Azkaban web server and a changing Ip address invalidates
    // the session
    final HashMap<String, String> headers = new HashMap<>();
    headers.put(WebUtils.X_FORWARDED_FOR_HEADER, req.getHeader(WebUtils.X_FORWARDED_FOR_HEADER.toLowerCase()));

    final WebUtils utils = new WebUtils();

    return utils.getRealClientIpAddr(headers, req.getRemoteAddr());
  }

  private Session getSessionFromRequest(final HttpServletRequest req)
          throws ServletException {
    final Cookie cookie = getCookieByName(req, SESSION_ID_NAME);
    String sessionId = null;
    String referer = req.getHeader("Referer");

    final Props props = this.application.getServerProps();
    String refererUrl = props.getString("azkaban.header.referer.url", "");

    if (cookie != null) {
      sessionId = cookie.getValue();
    }

    if (sessionId == null && hasParam(req, "session.id")) {
      sessionId = getParam(req, "session.id");
    }
    return getSessionFromSessionId(sessionId);
  }

  private Session getSessionFromSessionId(final String sessionId) {
    if (sessionId == null) {
      return null;
    }

    return getApplication().getSessionCache().getSession(sessionId);
  }

  private void handleLogin(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
    handleLogin(req, resp, null);
  }

  private void handleLogin(final HttpServletRequest req, final HttpServletResponse resp,
                           final String errorMsg) throws ServletException, IOException {
    String setCookie = resp.getHeader("Set-Cookie");
    if(null != setCookie){
      resp.setHeader("Set-Cookie", setCookie + ";Secure");
    }
    final Page page = newPage(req, resp, "azkaban/webapp/servlet/velocity/login.vm");

    String languageType = LoadJsonUtils.getLanguageType();
    Map<String, String> loginMap;
    Map<String, String> subPageMap1;
    if (languageType.equalsIgnoreCase("zh_CN")) {
      // 添加国际化标签
    loginMap = LoadJsonUtils.transJson("/com.webank.wedatasphere.schedulis.i18n.conf/azkaban-web-server-zh_CN.json",
            "azkaban.webapp.servlet.velocity.login.vm");
    subPageMap1 = LoadJsonUtils.transJson("/com.webank.wedatasphere.schedulis.i18n.conf/azkaban-web-server-zh_CN.json",
            "azkaban.webapp.servlet.velocity.nav.vm");
      this.passwordPlaceholder = "密码";
  }else {
    loginMap = LoadJsonUtils.transJson("/com.webank.wedatasphere.schedulis.i18n.conf/azkaban-web-server-en_US.json",
            "azkaban.webapp.servlet.velocity.login.vm");
    subPageMap1 = LoadJsonUtils.transJson("/com.webank.wedatasphere.schedulis.i18n.conf/azkaban-web-server-en_US.json",
            "azkaban.webapp.servlet.velocity.nav.vm");
      this.passwordPlaceholder = "Password";
  }
    loginMap.forEach(page::add);
    subPageMap1.forEach(page::add);

    page.add("passwordPlaceholder", this.passwordPlaceholder);
    if (errorMsg != null) {
      page.add("errorMsg", errorMsg);
    }
    page.add("currentlangType", languageType);
    page.render();
  }

  private void handleDssRequest(final HttpServletRequest req, final HttpServletResponse resp,Session session) throws ServletException, IOException {
    boolean dssEnable = true;
    if (dssEnable) {
      try {
          // DSS的AppJointAuth，用于判断是不是DSS发来的请求。
          AppJointAuth appJointAuth = AppJointAuth.getAppJointAuth();
          // 如果是DSS的请求，则可以从请求中拿到希望redirect的URL和DSS的登录用户
          RedirectMsg redirectMsg = appJointAuth.getRedirectMsg(req);
          String redirectUrl = redirectMsg.getRedirectUrl();
          String username = redirectMsg.getUser();
          logger.info("Succeed to get redirect url: {}, and username: {}" + redirectUrl + "," + username);
          // 通过用户名，完成无密码登录（将用户信息写入HttpSession）
          if(session == null){
            handleDssLoginAction(username, req,resp);
          }
          //登录成功，重定向到DSS指定的前端页面，以保证这个页面的请求，不会被Filter拦截
          resp.sendRedirect(redirectUrl);
      } catch (Exception e) {
        logger.error("Failed to redirect to other page, caused by: {}", e);
        resp.sendError(400,"Failed to redirect to other page.");
      }
    } else {
      resp.sendError(400,"DSS is not enabled.");
    }
  }

  @Override
  protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
          throws ServletException, IOException {
    Session session = getSessionFromRequest(req);
    this.webMetrics.markWebPostCall();
    logRequest(req, session);
    if (isIllegalPostRequest(req)) {
      writeResponse(resp, "Login error. Must pass username and password in request body");
      return;
    }
    final Props props = this.application.getServerProps();
    String nginxSSL = props.getString("nginx.ssl.module", "");
    if("open".equals(nginxSSL)) {
      String referer = req.getHeader("Referer");
      String refererUrl = props.getString("azkaban.header.referer.url", "");
      // 判断 Referer 是否以 bank.example 开头
      if ((referer != null) && !(referer.trim().startsWith(refererUrl))) {
        resp.sendRedirect("/error");
      }

      Map<String, String[]> param = req.getParameterMap();
      for(Map.Entry<String, String[]> entry : param.entrySet()){
        //XSS参数过滤
        String reqString = ObjectUtils.toString(entry.getValue()[0]);
        if(XSSFilterUtils.invalidStringFilter(reqString)){
          //handleErrorRequest(req, resp, "请不要输入非法字符串！");
          resp.sendRedirect("/error");
          return;
        }
      }
      if(XSSFilterUtils.invalidCookieFilter(req)){
        resp.sendRedirect("/error");
        return;
      }
    }
    // Handle Multipart differently from other post messages
    if (ServletFileUpload.isMultipartContent(req)) {
      final Map<String, Object> params = this.multipartParser.parseMultipart(req);
      if (session == null) {
        // See if the session id is properly set.
        if (params.containsKey("session.id")) {
          final String sessionId = (String) params.get("session.id");

          session = getSessionFromSessionId(sessionId);
          if (session != null) {
            handleMultiformPost(req, resp, params, session);
            return;
          }
        }

        // if there's no valid session, see if it's a one time session.
        if (!params.containsKey("username") || !params.containsKey("userpwd")) {
          writeResponse(resp, "Login error. Need username and password");
          return;
        }

        final String username = (String) params.get("username");
        final String password  = (String) params.get("userpwd");
        final String ip = getRealClientIpAddr(req);

        String superUser = (String)params.get(WTSS_SUPER_USER_STR);
        String superUserPwd = (String)params.get(WTSS_SUPER_USER_PWD_STR);
        if (WTSS_SUPER_USER.equals(superUser) && WTSS_SUPER_USER_PWD.equals(superUserPwd)){
          try{
            session = createSession(username, password, ip, superUser);
          } catch(final Exception e){
            writeResponse(resp, "Login error: " + e.getMessage());
            return;
          }
        }else{
          try {
            session = createSession(username, password, ip);
          } catch (final UserManagerException e) {
            writeResponse(resp, "Login error: " + e.getMessage());
            return;
          }
        }
      }
      handleMultiformPost(req, resp, params, session);
    } else if ("/checkin".equals(req.getRequestURI()) && hasParam(req, "action")
            && getParam(req, "action").equals("login")) {
      final HashMap<String, Object> obj = new HashMap<>();
      handleAjaxLoginAction(req, resp, obj);
      this.writeJSON(resp, obj);
    } else if (session == null) {
      if (hasParam(req, "username") && hasParam(req, "userpwd")) {
        // If it's a post command with curl, we create a temporary session
        try {
          session = createSession(req);
        } catch (final UserManagerException e) {
          writeResponse(resp, "Login error: " + e.getMessage());
        }
        handlePost(req, resp, session);
      } else {
        // There are no valid sessions and temporary logins, no we either pass
        // back a message or redirect.
        if (isAjaxCall(req)) {
          final String response =
                  createJsonResponse("error", "Invalid Session. Please login in again.",
                          "login", null);
          resp.setCharacterEncoding("utf-8");
          writeResponse(resp, response);
        } else {
          handleLogin(req, resp, "Enter username and password");
        }
      }
    } else {

      // 针对非页面操作的客户端,新增用户直接使用超级管理员
      boolean flag = featureAddSystemUserViaFastTrack(req, resp, "POST");

      // 如果flag是true, 说明未请求 addSystemUserViaFastTrack 接口,此处设置 flag 避免重复添加数据导致报错
      if (flag) {
        handlePost(req, resp, session);
      }
    }
  }

  /**
   * Disallows users from logging in by passing their username and password via the request header
   * where it'd be logged.
   *
   * Example of illegal post request: curl -X POST http://localhost:8081/?action=login\&username=azkaban\&password=azkaban
   *
   * req.getParameterMap() or req.getParameterNames() cannot be used because they draw no
   * distinction between the illegal request above and the following valid request: curl -X POST -d
   * "action=login&username=azkaban&password=azkaban" http://localhost:8081/
   *
   * "password=" is searched for because it leverages the query syntax to determine that the user is
   * passing the password as a parameter name. There is no other ajax call that has a parameter that
   * includes the string "password" at the end which could throw false positives.
   */
  private boolean isIllegalPostRequest(final HttpServletRequest req) {
    return (req.getQueryString() != null && req.getQueryString().contains("password="));
  }

    private Session createDssSession(final String username, final HttpServletRequest req)
            throws UserManagerException, ServletException, IOException {
      final String ip = getRealClientIpAddr(req);
      try {
        if (!StringUtils.isFromBrowser(req.getHeader("User-Agent"))) {
          logger.info("not browser.");
          Session cacheSession = this.application.getSessionCache().getSessionByUsername(username);
          if (cacheSession != null) {
            logger.info("session not found.");
            return cacheSession;
          }
        }
      } catch (final Exception e) {
        logger.error("no super user", e);
      }
      Session newSession = createSession(username, "dssToken", ip, req);
      return newSession;
    }


  private Session createSession(final HttpServletRequest req)
          throws UserManagerException, ServletException, IOException {
    final String username = getParam(req, "username");
    final String password = getParam(req, "userpwd");
    final String ip = getRealClientIpAddr(req);
    try{
      final String superUser = getParam(req, WTSS_SUPER_USER_STR);
      final String superUserPwd = getParam(req,  WTSS_SUPER_USER_PWD_STR);
      if (WTSS_SUPER_USER.equals(superUser) &&
              WTSS_SUPER_USER_PWD.equals(superUserPwd)){
        logger.info("superUser " + superUser + " signed username is " + username);
        //如果超级用户用户名和密码都是对的，那么我们直接放行
        if(!StringUtils.isFromBrowser(req.getHeader("User-Agent"))){
          logger.info("not browser.");
          Session cacheSession = this.application.getSessionCache().getSessionByUsername(username);
          if(cacheSession != null){
            logger.info("session not found.");
            return cacheSession;
          }
        }
        Session newSession = createSession(username, password, ip, superUser);
        getApplication().getSessionCache().addSession(newSession);
        return newSession;
      }
    }catch(final Exception e){
      logger.error("no super user", e);
      //没有超级用户，直接ignore
    }
    return createSession(username, password, ip, req);
  }

  private Session createSession(final String username, final String password, final String ip)
          throws UserManagerException, ServletException {
    final UserManager manager = getApplication().getTransitionService().getUserManager();
    final User user = manager.getUser(username, password);

    final String randomUID = UUID.randomUUID().toString();
    final Session session = new Session(randomUID, user, ip);

    return session;
  }

  private Session createSession(final String username, final String password, final String ip, HttpServletRequest request)
      throws UserManagerException, ServletException {
    final UserManager manager = getApplication().getTransitionService().getUserManager();
    User user = null;
    if("dssToken".equals(password)){
      user = new User(username);
    }else{
      user = manager.getUser(username, password);
    }
    if(!StringUtils.isFromBrowser(request.getHeader("User-Agent"))){
      logger.info("not browser.");
      Session cacheSession = this.application.getSessionCache().getSessionByUsername(username);
      if(cacheSession != null){
        logger.info("session not found.");
        return cacheSession;
      }
    }
    final String randomUID = UUID.randomUUID().toString();
    final Session session = new Session(randomUID, user, ip);
    getApplication().getSessionCache().addSession(session);
    return session;
  }


  private Session createSession(final String username, final String password, final String ip,
                                final String superUser) throws UserManagerException{

    UserManager manager = getApplication().getTransitionService().getUserManager();
    if (manager instanceof SystemUserManager){
      //不改接口，直接改SystemUserManager，这样做到少侵入
      SystemUserManager userManager = (SystemUserManager)manager;
      final User user = userManager.getUser(username, password, superUser);
      logger.info("User is " + user.toString());
      final String uuid = UUID.randomUUID().toString();
      return new Session(uuid, user, ip);
    }else{
      logger.warn("user manager 不是 WebankXmlUserManager 实例，不能进行创建session");
      return null;
    }
  }



  protected boolean hasPermission(final Project project, final User user, final Permission.Type type) {
    if (project.hasPermission(user, type)) {
      return true;
    }

    for (final String roleName : user.getRoles()) {
      final Role role = user.getRoleMap().get(roleName);
      if (role != null && role.getPermission().isPermissionSet(Permission.Type.ADMIN)) {
        return true;
      }
    }

    SystemManager systemManager = this.application.getTransitionService().getSystemManager();
    if (systemManager.isDepartmentMaintainer(user)) {
      return true;
    }

    return false;
  }

  protected void handleAjaxLoginAction(final HttpServletRequest req,
                                       final HttpServletResponse resp, final Map<String, Object> ret)
          throws ServletException {

    final Props props = this.application.getServerProps();
    String nginxSSL = props.getString("nginx.ssl.module", "");
    if("open".equals(nginxSSL)){
      //SSL模式下 cookie安全 为了通过 appscan 测试
//      List<String> cookieList = (ArrayList<String>) resp.getHeaders("Set-Cookie");
//      for(String setC : cookieList){
//        //setC = resp.getHeader("Set-Cookie");
//        resp.setHeader("Set-Cookie", setC + ";Secure");
//      }
      String setCookie = resp.getHeader("Set-Cookie");
      resp.setHeader("Set-Cookie", setCookie + ";Secure");
    }

    if (hasParam(req, "username") && hasParam(req, "userpwd")) {
      Session session = null;
      try {
        session = createSession(req);
      } catch (final UserManagerException | IOException e) {
        ret.put("error", "Login in error. " + e.getMessage());
        return;
      }
      if (null == session){
        logger.error("session is null");
        ret.put("error","Login in error, session is null.");
        return;
      }
      final Cookie cookie = new Cookie(SESSION_ID_NAME, session.getSessionId());
      cookie.setPath("/");
      if("open".equals(nginxSSL)) {
        cookie.setSecure(true);
        //限制web页面程序的browser端script程序读取cookie 开启可能会影响测试工具工作
        cookie.setHttpOnly(true);
      }
      resp.addCookie(cookie);

      ret.put("status", "success");
      ret.put("session.id", session.getSessionId());
      //登录成功后刷新用户所有Project的权限
      final ProjectManager projectManager = ((AzkabanWebServer) getApplication()).getProjectManager();
      final User user = session.getUser();
      final List<Project> projects = projectManager.getUserProjects(user);

      projects.stream().forEach(project -> {
        // 代理用户合集
        Set<String> proxySet = new HashSet<>();
        //添加当前用户的代理到项目中
        for(String pUser : user.getProxyUsers()){
          proxySet.add(pUser);
        }
        //代理用户更新时清理旧的代理用户
        project.removeAllProxyUsers();
        //添加当前用户的代理用户配置
        project.addAllProxyUsers(proxySet);
        //每次请求都更新当前项目的代理用户
        projectManager.updateProjectSetting(project);
      });
      //刷新缓存里的用户项目信息
      projectManager.loadAllProjects();
      projectManager.loadUserProjects(user);

    } else {
      ret.put("error", "Login in error.");
    }
  }

  protected void handleDssLoginAction(String username,final HttpServletRequest req,final HttpServletResponse resp)
          throws ServletException {
    final Props props = this.application.getServerProps();
    if (username!=null) {
      Session session = null;
      try {
        session = createDssSession(username,req);
      } catch (final UserManagerException | IOException e) {
        logger.error("Dss Login in error. " + e.getMessage());
        return;
      }
      if (null == session){
        logger.error("DSS Login in error, session is null.");
        return;
      }
      final Cookie cookie = new Cookie(SESSION_ID_NAME, session.getSessionId());
      cookie.setPath("/");
      resp.addCookie(cookie);
      logger.info("session.id {} " + session.getSessionId());
    } else {
      logger.error("Login in error,invalid username {}" + username);
    }
  }

  protected void writeResponse(final HttpServletResponse resp, final String response) throws IOException {
    final Writer writer = resp.getWriter();
    writer.append(response);
    writer.flush();
  }

  protected boolean isAjaxCall(final HttpServletRequest req) throws ServletException {
    final String value = req.getHeader("X-Requested-With");
    if (value != null) {
      logger.info("has X-Requested-With " + value);
      return value.equals("XMLHttpRequest");
    }
    final String ajaxString = req.getParameter("ajax");
    if(null != ajaxString && ajaxString.contains("ext")){
      return true;
    }

    return false;
  }

  /**
   * The get request is handed off to the implementor after the user is logged in.
   */
  protected abstract void handleGet(HttpServletRequest req,
                                    HttpServletResponse resp, Session session) throws ServletException,
          IOException;

  /**
   * The post request is handed off to the implementor after the user is logged in.
   */
  protected abstract void handlePost(HttpServletRequest req,
                                     HttpServletResponse resp, Session session) throws ServletException,
          IOException;

  /**
   * The post request is handed off to the implementor after the user is logged in.
   */
  protected void handleMultiformPost(final HttpServletRequest req,
                                     final HttpServletResponse resp, final Map<String, Object> multipart, final Session session)
          throws ServletException, IOException {
  }

  /**
   *
   * @param req
   * @param resp
   * @param errorMsg
   * @throws ServletException
   * @throws IOException
   */
  private void handleErrorRequest(final HttpServletRequest req, final HttpServletResponse resp,
                                  final String errorMsg) throws ServletException, IOException {
    final Page page = newPage(req, resp, "azkaban/webapp/servlet/velocity/single-error-page.vm");
    page.add("errorMsg", errorMsg);
    String languageType = LoadJsonUtils.getLanguageType();
    page.add("currentlangType", languageType);
    page.render();
    resp.sendError(230);
  }


}
