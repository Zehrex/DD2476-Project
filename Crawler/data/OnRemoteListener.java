2
https://raw.githubusercontent.com/Aivacom/JLYMeet-android/master/app/src/main/java/com/mediaroom/facade/OnRemoteListener.java
package com.mediaroom.facade;

/**
 *
 * The Listener interface about the state of Remote User
 *
 * ZH：
 * 远程用户状态监听器
 *
 * @author Aslan chenhengfei@yy.com
 * @date 2020/1/2
 */
public interface OnRemoteListener {
    void onJoinRoom(String uid);

    void onLeaveRoom(String uid);
}
