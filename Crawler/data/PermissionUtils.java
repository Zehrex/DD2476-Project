2
https://raw.githubusercontent.com/Aivacom/JLYAudio-android/master/app/src/main/java/com/mediaroom/utils/PermissionUtils.java
package com.mediaroom.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtils {

  public static final int REQUEST_CODE_PERMISSIONS = 2;

  /**
   * check permission
   *
   * @return true：Authorized； false：unauthorized；
   *
   * ZH:
   * 检测权限
   *
   * @return true：已授权； false：未授权；
   */
  public static boolean checkPermission(Context context, String permission) {
    if (ContextCompat.checkSelfPermission(context, permission)
        == PackageManager.PERMISSION_GRANTED) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Detect multiple permissions
   *
   * @return Unauthorized permissions
   *
   * ZH:
   * 检测多个权限
   *
   * @return 未授权的权限
   */
  public static List<String> checkMorePermissions(Context context, String[] permissions) {
    List<String> permissionList = new ArrayList<>();
    for (int i = 0; i < permissions.length; i++) {
      if (!checkPermission(context, permissions[i])) {
        permissionList.add(permissions[i]);
      }
    }
    return permissionList;
  }

  /**
   * Request permission
   *
   * ZH:
   * 请求权限
   */
  public static void requestPermission(Context context, String permission, int requestCode) {
    ActivityCompat.requestPermissions((Activity) context, new String[]{permission}, requestCode);
  }

  /**
   * Request multiple permissions
   *
   * ZH:
   * 请求多个权限
   */
  public static void requestMorePermissions(Context context, List permissionList, int requestCode) {
    String[] permissions = (String[]) permissionList.toArray(new String[permissionList.size()]);
    requestMorePermissions(context, permissions, requestCode);
  }

  /**
   * Request multiple permissions
   *
   * ZH:
   *
   * 请求多个权限
   */
  public static void requestMorePermissions(Context context, String[] permissions,
      int requestCode) {
    ActivityCompat.requestPermissions((Activity) context, permissions, requestCode);
  }

  /**
   * Determine if permission has been denied
   *
   * @describe :If the application has requested this permission before but the user refuses, this method will return true;
   *            If the application requests permission for the first time or the user has denied the permission request in the past,
   *            and the 'Don't ask again' option is selected in the permission request system dialog box, this method will return false.
   *
   * ZH:
   * 判断是否已拒绝过权限
   *
   * @describe :如果应用之前请求过此权限但用户拒绝，此方法将返回 true;
   *            如果应用第一次请求权限或 用户在过去拒绝了权限请求，并在权限请求系统对话框中选择了 Don't ask again 选项，此方法将返回 false。
   */
  public static boolean judgePermission(Context context, String permission) {
    if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permission)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Check permission and request permission: if there is no permission, request permission
   *
   * ZH:
   * 检测权限并请求权限：如果没有权限，则请求权限
   */
  public static void checkAndRequestPermission(Context context, String permission,
      int requestCode) {
    if (!checkPermission(context, permission)) {
      requestPermission(context, permission, requestCode);
    }
  }

  /**
   * Detect and request multiple permissions
   *
   * ZH:
   * 检测并请求多个权限
   */
  public static void checkAndRequestMorePermissions(Context context, String[] permissions,
      int requestCode) {
    List<String> permissionList = checkMorePermissions(context, permissions);
    requestMorePermissions(context, permissionList, requestCode);
  }


  /**
   * check permission
   *
   * @describe: The specific implementation is determined by the callback interface
   *
   * ZH:
   * 检测权限
   *
   * @describe：具体实现由回调接口决定
   */
  public static void checkPermission(Context context, String permission,
      PermissionCheckCallBack callBack) {
    if (checkPermission(context, permission)) { // 用户已授予权限（User has granted permission）
      callBack.onHasPermission();
    } else {
      if (judgePermission(context, permission))  // 用户之前已拒绝过权限申请(User has previously denied permission application)
      {
        callBack.onUserHasAlreadyTurnedDown(permission);
      } else                                       // 用户之前已拒绝并勾选了不在询问、用户第一次申请权限。(The user has previously rejected and checked the first time the user is not asking for permission.)
      {
        callBack.onUserHasAlreadyTurnedDownAndDontAsk(permission);
      }
    }
  }

  /**
   * Detect multiple permissions
   *
   * @describe: The specific implementation is determined by the callback interface
   *
   * ZH:
   * 检测多个权限
   *
   * @describe：具体实现由回调接口决定
   */
  public static void checkMorePermissions(Context context, String[] permissions,
      PermissionCheckCallBack callBack) {
    List<String> permissionList = checkMorePermissions(context, permissions);
    if (permissionList.size() == 0) {  // 用户已授予权限（User has granted permission）
      callBack.onHasPermission();
    } else {
      boolean isFirst = true;
      for (int i = 0; i < permissionList.size(); i++) {
        String permission = permissionList.get(i);
        if (judgePermission(context, permission)) {
          isFirst = false;
          break;
        }
      }
      String[] unauthorizedMorePermissions = (String[]) permissionList
          .toArray(new String[permissionList.size()]);
      if (isFirst)// 用户之前已拒绝过权限申请(User has previously denied permission application)
      {
        callBack.onUserHasAlreadyTurnedDownAndDontAsk(unauthorizedMorePermissions);
      } else       // 用户之前已拒绝并勾选了不在询问、用户第一次申请权限。(The user has previously rejected and checked the first time the user is not asking for permission.)
      {
        callBack.onUserHasAlreadyTurnedDown(unauthorizedMorePermissions);
      }

    }
  }


  /**
   * Detect and apply for permission
   *
   * ZH:
   * 检测并申请权限
   */
  public static void checkAndRequestPermission(Context context, String permission, int requestCode,
      PermissionRequestSuccessCallBack callBack) {
    if (checkPermission(context, permission)) {// 用户已授予权限（User has granted permission）
      callBack.onHasPermission();
    } else {
      requestPermission(context, permission, requestCode);
    }
  }

  /**
   * Detect and apply for multiple permissions
   *
   * ZH:
   * 检测并申请多个权限
   */
  public static void checkAndRequestMorePermissions(Context context, String[] permissions,
      int requestCode, PermissionRequestSuccessCallBack callBack) {
    List<String> permissionList = checkMorePermissions(context, permissions);
    if (permissionList.size() == 0) {  // 用户已授予权限（User has granted permission）
      callBack.onHasPermission();
    } else {
      requestMorePermissions(context, permissionList, requestCode);
    }
  }

  /**
   * Determine whether the permission is successfully applied
   *
   * ZH:
   * 判断权限是否申请成功
   */
  public static boolean isPermissionRequestSuccess(int[] grantResults) {
    if (grantResults.length > 0
        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Callback for user application permission result
   *
   * ZH:
   * 用户申请权限返回
   */
  public static void onRequestPermissionResult(Context context, String permission,
      int[] grantResults, PermissionCheckCallBack callback) {
    if (PermissionUtils.isPermissionRequestSuccess(grantResults)) {
      callback.onHasPermission();
    } else {
      if (PermissionUtils.judgePermission(context, permission)) {
        callback.onUserHasAlreadyTurnedDown(permission);
      } else {
        callback.onUserHasAlreadyTurnedDownAndDontAsk(permission);
      }
    }
  }

  /**
   * Callback when user applies for multiple permissions
   *
   * ZH:
   * 用户申请多个权限返回
   */
  public static void onRequestMorePermissionsResult(Context context, String[] permissions,
      PermissionCheckCallBack callback) {
    boolean isBannedPermission = false;
    List<String> permissionList = checkMorePermissions(context, permissions);
    if (permissionList.size() == 0) {
      callback.onHasPermission();
    } else {
      for (int i = 0; i < permissionList.size(); i++) {
        if (!judgePermission(context, permissionList.get(i))) {
          isBannedPermission = true;
          break;
        }
      }
      //Prohibited asking for permission again
      //　已禁止再次询问权限
      if (isBannedPermission) {
        callback.onUserHasAlreadyTurnedDownAndDontAsk(permissions);
      } else // 拒绝权限（Permission denied）
      {
        callback.onUserHasAlreadyTurnedDown(permissions);
      }
    }

  }


  /**
   * Jump to permission setting page
   *
   * ZH：
   * 跳转到权限设置界面
   */
  public static void toAppSetting(Context context) {
    Intent intent = new Intent();
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    if (Build.VERSION.SDK_INT >= 9) {
      intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
      intent.setData(Uri.fromParts("package", context.getPackageName(), null));
    } else if (Build.VERSION.SDK_INT <= 8) {
      intent.setAction(Intent.ACTION_VIEW);
      intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
      intent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
    }
    context.startActivity(intent);
  }

  public interface PermissionRequestSuccessCallBack {

    /**
     * User has granted permission
     *
     * ZH:
     * 用户已授予权限
     */
    void onHasPermission();
  }


  public interface PermissionCheckCallBack {

    /**
     * User has granted permission
     *
     * ZH:
     * 用户已授予权限
     */
    void onHasPermission();

    /**
     * User has denied permission
     *
     * ZH:
     * 用户已拒绝过权限
     */
    void onUserHasAlreadyTurnedDown(String... permission);

    /**
     * The user has rejected it and has checked the option 'Do not ask me again', the user applies for permission for the first time;
     *
     * ZH:
     * 用户已拒绝过并且已勾选不再询问选项、用户第一次申请权限;
     */
    void onUserHasAlreadyTurnedDownAndDontAsk(String... permission);
  }


}