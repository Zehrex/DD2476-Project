18
https://raw.githubusercontent.com/WeBankFinTech/Schedulis/master/azkaban-common/src/main/java/com/webank/wedatasphere/schedulis/common/jobExecutor/utils/SystemBuiltInParamJodeTimeUtils.java
/*
 * Copyright 2020 WeBank
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.webank.wedatasphere.schedulis.common.jobExecutor.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import azkaban.executor.ExecutableFlow;
import org.joda.time.format.DateTimeFormatter;

public class SystemBuiltInParamJodeTimeUtils {

  private static final Logger utilLogger = Logger.getLogger(SystemBuiltInParamJodeTimeUtils.class);
  public static final String RUN_TODAY = "run_today";
  public static final String RUN_TODAY_STD = "run_today_std";
  public static final String RUN_DATE = "run_date";
  public static final String RUN_DATE_STD = "run_date_std";
  public static final String RUN_MONTH_BEGIN = "run_month_begin";
  public static final String RUN_MONTH_BEGIN_STD = "run_month_begin_std";
  public static final String RUN_MONTH_END = "run_month_end";
  public static final String RUN_MONTH_END_STD = "run_month_end_std";
  public static final String MINUS = "MINUS";
  public static final String PLUS = "PLUS";
  public static final String TIME_TEMPLATE = "(\\d{4}\\.\\d{2}\\.\\d{2}|\\d{4}/\\d{2}/\\d{2}|\\d{4}-\\d{2}-\\d{2}|\\d{4}\\d{2}\\d{2})";

  private Map<String, String> propMap = new HashMap<>();

  private Map<String, LocalDate> defaultDate = new HashMap<>();;

  private void initDate(ExecutableFlow executableFlow) throws RuntimeException{
    LocalDate runDate = null;
    DateTimeFormatter dateTimeFormatter0 = DateTimeFormat.forPattern("yyyyMMdd");
    DateTimeFormatter dateTimeFormatter1 = DateTimeFormat.forPattern("yyyy-MM-dd");
    //历史重跑
    if(2 == executableFlow.getFlowType()){
      runDate = new LocalDate(Long.valueOf(executableFlow.getRepeatOption().get("startTimeLong"))).minusDays(1);
    } else {
      if (null != executableFlow.getExecutionOptions().getFlowParameters().get(RUN_DATE)) {
        runDate = LocalDate.parse(executableFlow.getExecutionOptions().getFlowParameters().get(RUN_DATE), dateTimeFormatter0);
      } else if(this.propMap.get(RUN_DATE) != null) {
        String tmp = this.propMap.get(RUN_DATE).replaceAll("[\"'./-]","");
        runDate = LocalDate.parse(tmp, dateTimeFormatter0);
      } else {
        runDate = new LocalDate(executableFlow.getSubmitTime()).minusDays(1);
      }
    }
    //用于前端显示
    executableFlow.setRunDate(runDate.toString("yyyyMMdd"));
    defaultDate.put(RUN_DATE, runDate);
    defaultDate.put(RUN_DATE_STD, runDate);
    defaultDate.put(RUN_TODAY, runDate.plusDays(1));
    defaultDate.put(RUN_TODAY_STD, runDate.plusDays(1));
    defaultDate.put(RUN_MONTH_BEGIN, runDate.dayOfMonth().withMinimumValue());
    defaultDate.put(RUN_MONTH_BEGIN_STD, runDate.dayOfMonth().withMinimumValue());
    defaultDate.put(RUN_MONTH_END, runDate.dayOfMonth().withMaximumValue());
    defaultDate.put(RUN_MONTH_END_STD, runDate.dayOfMonth().withMaximumValue());

    if(2 != executableFlow.getFlowType()){
      LocalDate newRunDateStd = hasNewDate(executableFlow, RUN_DATE_STD, dateTimeFormatter1);
      if(newRunDateStd != null){
        defaultDate.put(RUN_DATE_STD, newRunDateStd);
      }
      LocalDate newRunToday = hasNewDate(executableFlow, RUN_TODAY, dateTimeFormatter0);
      if(newRunToday != null){
        defaultDate.put(RUN_TODAY, newRunToday);
      }
      LocalDate newRunTodayStd = hasNewDate(executableFlow, RUN_TODAY_STD, dateTimeFormatter1);
      if(newRunTodayStd != null){
        defaultDate.put(RUN_TODAY_STD, newRunTodayStd);
      }
      LocalDate newRunMonthBegin = hasNewDate(executableFlow, RUN_MONTH_BEGIN, dateTimeFormatter0);
      if(newRunMonthBegin != null){
        defaultDate.put(RUN_MONTH_BEGIN, newRunMonthBegin);
      }
      LocalDate newRunMonthBeginStd = hasNewDate(executableFlow, RUN_MONTH_BEGIN_STD, dateTimeFormatter1);
      if(newRunMonthBeginStd != null){
        defaultDate.put(RUN_MONTH_BEGIN_STD, newRunMonthBeginStd);
      }
      LocalDate newRunMonthEnd = hasNewDate(executableFlow, RUN_MONTH_END, dateTimeFormatter0);
      if(newRunMonthEnd != null){
        defaultDate.put(RUN_MONTH_END, newRunMonthEnd);
      }
      LocalDate newRunMonthEndStd = hasNewDate(executableFlow, RUN_MONTH_END_STD, dateTimeFormatter1);
      if(newRunMonthEndStd != null){
        defaultDate.put(RUN_MONTH_END_STD, newRunMonthEndStd);
      }
    }

  }

  private LocalDate hasNewDate(ExecutableFlow executableFlow, String dateType, DateTimeFormatter dateTimeFormatter){
    LocalDate newDate = null;
    if (null != executableFlow.getExecutionOptions().getFlowParameters().get(dateType)) {
      newDate = LocalDate.parse(executableFlow.getExecutionOptions().getFlowParameters().get(dateType), dateTimeFormatter);
    } else if(this.propMap.get(dateType) != null) {
      newDate = LocalDate.parse(this.propMap.get(dateType), dateTimeFormatter);
    }
    return newDate;
  }

  //写入内容到文件
  private void FileWrite(String filePath, String fileStr){
    FileWriter fw = null;
    try {
      fw = new FileWriter(filePath);
      //写入到文件
      fw.write(fileStr);
    } catch (Exception e) {
      utilLogger.error("写入脚本文件异常！", e);
      e.printStackTrace();
    }finally {
      if(fw != null){
        try {
          fw.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  //获取所有用户配置文件路径
  private List<String> loadAllPropertiesFile(String workingDir){
    List<String> propPathList = new ArrayList<>();

    findPropPath(workingDir, propPathList);

    return propPathList;
  }

  //递归查找配置文件路径
  private void findPropPath(String dirPath, List<String> filePathList){
    File f = new File(dirPath);
    if (!f.exists()) {
      System.out.println(dirPath + " not exists");
    }
    File fa[] = f.listFiles();
    for (int i = 0; i < fa.length; i++) {
      File fs = fa[i];
      if (fs.isDirectory()) {
        findPropPath(fs.getPath(), filePathList);
      } else {
        if(fs.getName().endsWith(".properties")){
          filePathList.add(fs.getPath().toString());
        }
      }
    }
  }

  //获取所有脚本文件地址
  private List<String> loadAllScriptFile(String workingDir){
    List<String> scriptPathList = new ArrayList<>();

    findScriptFilePath(workingDir, scriptPathList);

    return scriptPathList;
  }

  //递归脚本目录
  private void findScriptFilePath(String dirPath, List<String> filePathList){
    File f = new File(dirPath);
    if (!f.exists()) {
      //System.out.println(dirPath + " not exists");
      utilLogger.error("文件地址: " + dirPath + "不存在！");
    }
    File fa[] = f.listFiles();
    for (int i = 0; i < fa.length; i++) {
      File fs = fa[i];
      if (fs.isDirectory()) {
        findScriptFilePath(fs.getPath(), filePathList);
      } else {
        if(fs.getName().endsWith(".py") || fs.getName().endsWith(".sh")
            || fs.getName().endsWith(".sql") || fs.getName().endsWith(".hql")
            || fs.getName().endsWith(".job") || fs.getName().endsWith(".flow")){
          filePathList.add(fs.getPath().toString());
        }
      }
    }
  }

  //读取propertie文件内容
  private Map<String, String> readProperties(String propPath){
    Map<String, String> propMap = new HashMap<>();
    Properties prop = new Properties();
    InputStream input = null;
    try {
      input = new FileInputStream(propPath);
      // load a properties file
      prop.load(input);

      if(!prop.isEmpty()){
        for (Map.Entry<Object, Object> entry : prop.entrySet()) {
          String key = String.valueOf(entry.getKey());
          String value = String.valueOf(entry.getValue());
          propMap.put(key, value);
        }
      }
    } catch (Exception ex) {
      utilLogger.error("读取properties配置文件异常！", ex);
      ex.printStackTrace();
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return propMap;
  }

  //读取文件
  private String readFile(String filePath)
  {
    StringBuilder sb = new StringBuilder();
    BufferedReader br = null;
    try {
      File file = new File(filePath);
      br = new BufferedReader(new FileReader(file));
      char[] data = new char[1024];
      int rn = 0;
      String line = "";
//      while ((rn = br.read(data)) > 0){
//        String st = String.valueOf(data, 0, rn);
//        sb.append(st);
//      }
      while ((line = br.readLine()) != null){
        sb.append(line).append("\n");
      }
      String fileStr = sb.toString();

      return fileStr;
    } catch (Exception e) {
      utilLogger.error("读取脚本文件异常！", e);
      e.printStackTrace();
    } finally {
      if(br != null){
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return sb.toString();
  }



  //用脚本内容字符串中解析出需要替换的参数
  private Map<String, String> paramDecompose(String fileStr, ExecutableFlow ef){

    Map<String, String> paramReplaceMap = new HashMap<>();

    Pattern pattern = Pattern.compile("\\$\\{([^\\}]+)\\}");
    Matcher matcher = pattern.matcher(fileStr);
    while(matcher.find()){
      String fullStr = matcher.group();  // fullStr = ${abcd}
      String valueStr = matcher.group(1); // valueStr = abcd
      String timeParam = scriptTimeHandle(valueStr);
      if(!"".equals(timeParam)) {
        paramReplaceMap.put(fullStr, timeParam);
      }

    }

    return paramReplaceMap;
  }

  private String scriptTimeHandle(String param) {

    //时间字符串
    String timeStr = "";
    param = param.replaceAll("\\s*", "");

    if (RUN_DATE.equals(param)) {
      timeStr = defaultDate.get(RUN_DATE).toString("yyyyMMdd");

    } else if (RUN_DATE_STD.equals(param)) {
      timeStr = defaultDate.get(RUN_DATE_STD).toString("yyyy-MM-dd");

    } else if (param.contains(RUN_DATE) && !param.contains(RUN_DATE_STD)) {
      String mathStr = StringUtils.substringAfter(param, RUN_DATE);
      String[] sAry = {};
      if (MINUS.equals(paramVerify(mathStr, param))) {
        sAry = mathStr.split("-");
        timeStr = defaultDate.get(RUN_DATE).minusDays(Integer.valueOf(sAry[1])).toString("yyyyMMdd");
      } else if (PLUS.equals(paramVerify(mathStr, param))) {
        sAry = mathStr.split("\\+");
        timeStr = defaultDate.get(RUN_DATE).plusDays(Integer.valueOf(sAry[1])).toString("yyyyMMdd");
      }

    } else if (param.contains(RUN_DATE) && param.contains(RUN_DATE_STD)) {
      String mathStr = StringUtils.substringAfter(param, RUN_DATE_STD);
      String[] sAry = {};
      if (MINUS.equals(paramVerify(mathStr, param))) {
        sAry = mathStr.split("-");
        timeStr = defaultDate.get(RUN_DATE_STD).minusDays(Integer.valueOf(sAry[1])).toString();
      } else if (PLUS.equals(paramVerify(mathStr, param))) {
        sAry = mathStr.split("\\+");
        timeStr = defaultDate.get(RUN_DATE_STD).plusDays(Integer.valueOf(sAry[1])).toString();
      }
    }

    if (RUN_MONTH_BEGIN.equals(param)) {
      timeStr = defaultDate.get(RUN_MONTH_BEGIN).toString("yyyyMMdd");

    } else if (RUN_MONTH_BEGIN_STD.equals(param)) {
      timeStr = defaultDate.get(RUN_MONTH_BEGIN_STD).toString("yyyy-MM-dd");

    } else if (param.contains(RUN_MONTH_BEGIN) && !param.contains(RUN_MONTH_BEGIN_STD)) {
      String mathStr = StringUtils.substringAfter(param, RUN_MONTH_BEGIN);
      String[] sAry = {};
      if (MINUS.equals(paramVerify(mathStr, param))) {
        sAry = mathStr.split("-");
        timeStr = defaultDate.get(RUN_MONTH_BEGIN).minusMonths(Integer.valueOf(sAry[1])).dayOfMonth()
            .withMinimumValue().toString("yyyyMMdd");

      } else if (PLUS.equals(paramVerify(mathStr, param))) {
        sAry = mathStr.split("\\+");
        timeStr = defaultDate.get(RUN_MONTH_BEGIN).plusMonths(Integer.valueOf(sAry[1])).dayOfMonth()
            .withMinimumValue().toString("yyyyMMdd");
      }

    } else if (param.contains(RUN_MONTH_BEGIN) && param.contains(RUN_MONTH_BEGIN_STD)) {
      String mathStr = StringUtils.substringAfter(param, RUN_MONTH_BEGIN_STD);
      String[] sAry = {};
      if (MINUS.equals(paramVerify(mathStr, param))) {
        sAry = mathStr.split("-");
        timeStr = defaultDate.get(RUN_MONTH_BEGIN_STD).minusMonths(Integer.valueOf(sAry[1])).dayOfMonth()
            .withMinimumValue().toString("yyyy-MM-dd");

      } else if (PLUS.equals(paramVerify(mathStr, param))) {
        sAry = mathStr.split("\\+");
        timeStr = defaultDate.get(RUN_MONTH_BEGIN_STD).plusMonths(Integer.valueOf(sAry[1])).dayOfMonth()
            .withMinimumValue().toString("yyyy-MM-dd");
      }
    }

    if(RUN_MONTH_END.equals(param)){
      timeStr = defaultDate.get(RUN_MONTH_END).toString("yyyyMMdd");

    }else if(RUN_MONTH_END_STD.equals(param)){
      timeStr = defaultDate.get(RUN_MONTH_END_STD).toString("yyyy-MM-dd");

    }else if(param.contains(RUN_MONTH_END) && !param.contains(RUN_MONTH_END_STD)){
      String mathStr = StringUtils.substringAfter(param, RUN_MONTH_END);
      String[] sAry = {};

      if(MINUS.equals(paramVerify(mathStr, param))) {
        sAry = mathStr.split("-");
        timeStr = defaultDate.get(RUN_MONTH_END).minusMonths(Integer.valueOf(sAry[1])).dayOfMonth()
            .withMaximumValue().toString("yyyyMMdd");

      } else if(PLUS.equals(paramVerify(mathStr, param))) {
        sAry = mathStr.split("\\+");
        timeStr = defaultDate.get(RUN_MONTH_END).plusMonths(Integer.valueOf(sAry[1])).dayOfMonth()
            .withMaximumValue().toString("yyyyMMdd");

      }

    }else if(param.contains(RUN_MONTH_END) && param.contains(RUN_MONTH_END_STD)){
      String mathStr = StringUtils.substringAfter(param, RUN_MONTH_END_STD);
      String[] sAry = {};

      if(MINUS.equals(paramVerify(mathStr, param))) {
        sAry = mathStr.split("-");
        timeStr = defaultDate.get(RUN_MONTH_END_STD).minusMonths(Integer.valueOf(sAry[1])).dayOfMonth()
            .withMaximumValue().toString("yyyy-MM-dd");

      } else if(PLUS.equals(paramVerify(mathStr, param))) {
        sAry = mathStr.split("\\+");
        timeStr = defaultDate.get(RUN_MONTH_END_STD).plusMonths(Integer.valueOf(sAry[1])).dayOfMonth()
            .withMaximumValue().toString("yyyy-MM-dd");
      }
    }

    if (RUN_TODAY.equals(param)) {
      timeStr = defaultDate.get(RUN_TODAY).toString("yyyyMMdd");
    } else if (RUN_TODAY_STD.equals(param)) {
      timeStr = defaultDate.get(RUN_TODAY_STD).toString("yyyy-MM-dd");
    } else if (param.contains(RUN_TODAY) && !param.contains(RUN_TODAY_STD)) {
      String mathStr = StringUtils.substringAfter(param, RUN_TODAY);
      String[] sAry = {};
      if (MINUS.equals(paramVerify(mathStr, param))) {
        sAry = mathStr.split("-");
        timeStr = defaultDate.get(RUN_TODAY).minusDays(Integer.valueOf(sAry[1])).toString("yyyyMMdd");
      } else if (PLUS.equals(paramVerify(mathStr, param))) {
        sAry = mathStr.split("\\+");
        timeStr = defaultDate.get(RUN_TODAY).plusDays(Integer.valueOf(sAry[1])).toString("yyyyMMdd");
      }
    } else if (param.contains(RUN_TODAY) && param.contains(RUN_TODAY_STD)) {
      String mathStr = StringUtils.substringAfter(param, RUN_TODAY_STD);
      String[] sAry = {};
      if (MINUS.equals(paramVerify(mathStr, param))) {
        sAry = mathStr.split("-");
        timeStr = defaultDate.get(RUN_TODAY_STD).minusDays(Integer.valueOf(sAry[1])).toString("yyyy-MM-dd");
      } else if (PLUS.equals(paramVerify(mathStr, param))) {
        sAry = mathStr.split("\\+");
        timeStr = defaultDate.get(RUN_TODAY_STD).plusDays(Integer.valueOf(sAry[1])).toString("yyyy-MM-dd");
      }
    }

    return timeStr;
  }

  //过滤用户设置的参数 排除用户设置过的参数
  private void filterUserParam(Map<String, String> systemParam, ExecutableFlow ef){
    Map<String, String> handleMap = new HashMap<>();
    handleMap.putAll(systemParam);
    for(String userKey : getPropMap().keySet()){
      for(String systemKey : handleMap.keySet()){
        if(systemKey.contains(userKey) && !userKey.contains("_std")
            && !systemKey.contains(userKey + "_std")){
          systemParam.remove(systemKey);
        }
        if(systemKey.contains(userKey) && userKey.contains("_std")){
          systemParam.remove(systemKey);
        }
      }
    }
    for(String userKey : ef.getExecutionOptions().getFlowParameters().keySet()){
      if(null != handleMap.get("${" + userKey + "}")){
        systemParam.remove("${" + userKey + "}");
      }
    }
    // TODO 过滤用户变量
    for(String userKey : ef.getUserProps().keySet()){
      if(null != handleMap.get("${" + userKey + "}")){
        systemParam.remove("${" + userKey + "}");
      }
    }

  }

  //内置参数处理主流程
  public void run(String workingDir, ExecutableFlow ef){
    //用户propertie文件路径集合
    List<String> propPathList = loadAllPropertiesFile(workingDir);
    //获取用户所有配置文件参数
    for(String filePath : propPathList){
      //项目文件中的properties中的配置参数
      getPropMap().putAll(readProperties(filePath));
    }

    try{
      if(ef.getOtherOption().get("run_date") == null){
        if(this.getPropMap().get("run_date") != null && !this.getPropMap().get("run_date").isEmpty()){
          ef.getOtherOption().put("run_date", this.getPropMap().get("run_date"));
        }
      }
    } catch(RuntimeException e){
      utilLogger.error("set rundate failed {}", e);
    }

    //所有脚本的文件地址
    List<String> scriptPathList = loadAllScriptFile(workingDir);
    //初始化默认run_date日期和其他相关日期
    boolean initDateIsSuccess = true;
    try {
      initDate(ef);
    } catch (RuntimeException re){
      initDateIsSuccess = false;
      utilLogger.error("parse run_date failed.", re);
    }
    //循环脚本文件地址
    for(String filePath : scriptPathList){
      //读取单个脚本文件的内容
      String fileStr = readFile(filePath);
      if(initDateIsSuccess) {
        //获取单个脚本中需要替换的参数
        Map<String, String> scriptMap = paramDecompose(fileStr, ef);
        if (scriptMap != null && scriptMap.size() != 0) {
          //循环替换脚本中对应的参数内容
          for (String timeStr : scriptMap.keySet()) {
            fileStr = StringUtils.replace(fileStr, timeStr, scriptMap.get(timeStr));
          }
        }
      }
      //将替换后的内容重新写入到脚本文件中,重写后文件变成unix格式
      FileWrite(filePath, fileStr);
    }
  }

  public Map<String, String> getPropMap() {
    return propMap;
  }

  public void setPropMap(Map<String, String> propMap) {
    this.propMap = propMap;
  }

  public void addPropMap(String key, String value) {
    this.propMap.put(key, value);
  }


  private String paramVerify(String param, String fullParam){

    String symbol = "";

    String reg = "[0-9]";

    String[] mathStr = {};

    int minusSite = param.indexOf("-");
    int plusSite = param.indexOf("+");

    String[] sAry = null;

    if((plusSite > minusSite && minusSite != -1) || plusSite == -1){
      sAry = param.split("-");
      symbol = MINUS;
    } else if ((minusSite > plusSite && plusSite != -1) || minusSite == -1){
      sAry = param.split("\\+");
      symbol = PLUS;
    }

    if(fullParam.contains(RUN_DATE) && !fullParam.contains(RUN_DATE_STD)){
      mathStr = StringUtils.split(fullParam, RUN_DATE);
    } else if (fullParam.contains(RUN_DATE) && fullParam.contains(RUN_DATE_STD)) {
      mathStr = StringUtils.split(param, RUN_DATE_STD);
    } else if (fullParam.contains(RUN_MONTH_BEGIN) && !fullParam.contains(RUN_MONTH_BEGIN_STD)){
      mathStr = StringUtils.split(param, RUN_MONTH_BEGIN);
    } else if (fullParam.contains(RUN_MONTH_BEGIN) && fullParam.contains(RUN_MONTH_BEGIN_STD)){
      mathStr = StringUtils.split(param, RUN_MONTH_BEGIN_STD);
    } else if(fullParam.contains(RUN_MONTH_END) && !fullParam.contains(RUN_MONTH_END_STD)){
      mathStr = StringUtils.split(param, RUN_MONTH_END);
    } else if(fullParam.contains(RUN_MONTH_END) && fullParam.contains(RUN_MONTH_END_STD)){
      mathStr = StringUtils.split(fullParam, RUN_MONTH_END_STD);
    }else if(fullParam.contains(RUN_TODAY) && !fullParam.contains(RUN_TODAY_STD)){
      mathStr = StringUtils.split(fullParam, RUN_TODAY);
    } else if (fullParam.contains(RUN_TODAY) && fullParam.contains(RUN_TODAY_STD)) {
      mathStr = StringUtils.split(param, RUN_TODAY_STD);
    }

    if(mathStr.length > 0){
      Pattern pattern = Pattern.compile(reg);
      if(!pattern.matcher(mathStr[0]).find()){
        utilLogger.error("脚本替换参数适配异常！请检查脚本！");
        utilLogger.error("The script parameter ${" + fullParam + "} exception!Please check the script!");
        symbol = "FALSE";
      }
    }

    if(sAry.length > 1 && sAry.length == 2){
      String start = sAry[0];
      if(StringUtils.isNotEmpty(start)){
        utilLogger.error("脚本替换参数适配异常！请检查脚本！");
        utilLogger.error("The script parameter ${" + fullParam + "} exception!Please check the script!");
        symbol = "FALSE";
      }
      String str = sAry[1];
      Pattern pattern = Pattern.compile("[0-9]*");
      if(!pattern.matcher(str).matches()){
        utilLogger.error("脚本替换参数适配异常！请检查脚本！");
        utilLogger.error("The script parameter ${" + fullParam + "} exception!Please check the script!");
        symbol = "FALSE";
      }
    }else if(sAry.length > 2){//多个运算符号就报异常
      utilLogger.error("脚本替换参数适配异常！请检查脚本！");
      utilLogger.error("The script parameter ${" + fullParam + "} exception!Please check the script!");
      symbol = "FALSE";
    }else if(sAry.length <= 1){//多个运算符号就报异常
      utilLogger.error("脚本替换参数适配异常！请检查脚本！");
      utilLogger.error("The script parameter ${" + fullParam + "} exception!Please check the script!");
      symbol = "FALSE";
    }

    return symbol;
  }


  private long flowParamTimesHandle(ExecutableFlow ef){
    long flowParamSetTime = 0;
    org.joda.time.format.DateTimeFormatter formatter;
    if(null != ef.getExecutionOptions().getFlowParameters().get("run_date")){
      formatter = DateTimeFormat.forPattern("yyyyMMdd");
      LocalDate localDate = LocalDate.parse(ef.getExecutionOptions().getFlowParameters().get("run_date")
          , formatter);
      flowParamSetTime = localDate.toDate().getTime();
    } else {

    }
    return flowParamSetTime;
  }

  public static boolean dateFormatCheck(String date){
    Pattern p = Pattern.compile(TIME_TEMPLATE);
    Matcher m = p.matcher(date);
    if(m.matches()) {
      return true;
    } else {
      utilLogger.error(date + "，不是合法的日期格式！");
      return false;
    }
  }


}
