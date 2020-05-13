2
https://raw.githubusercontent.com/Aivacom/JLYAudio-android/master/app/src/main/java/com/mediaroom/facade/MyThunderEventHandler.java
package com.mediaroom.facade;

import com.thunder.livesdk.ThunderEventHandler;
import com.thunder.livesdk.ThunderNotification;

/**
 *
 * Thunder Event Callback Class
 *
 * ZH：
 * ThunderEnentHandler 的回调接口
 *
 * @author Aslan chenhengfei@yy.com
 * @since 2019年12月18日
 */
public class MyThunderEventHandler extends ThunderEventHandler {

    @Override
    public void onError(int error) {

    }

    @Override
    public void onJoinRoomSuccess(String room, String uid, int elapsed) {

    }

    @Override
    public void onLeaveRoom(RoomStats status) {

    }

    @Override
    public void onBizAuthResult(boolean bPublish, int result) {

    }

    @Override
    public void onSdkAuthResult(int result) {

    }

    @Override
    public void onUserBanned(boolean status) {

    }

    @Override
    public void onUserJoined(String uid, int elapsed) {

    }

    @Override
    public void onUserOffline(String uid, int reason) {

    }

    @Override
    public void onTokenWillExpire(byte[] token) {

    }

    @Override
    public void onTokenRequested() {

    }

    @Override
    public void onNetworkQuality(String uid, int txQuality, int rxQuality) {

    }

    @Override
    public void onRoomStats(ThunderNotification.RoomStats stats) {

    }

    @Override
    public void onPlayVolumeIndication(AudioVolumeInfo[] speakers, int totalVolume) {

    }

    @Override
    public void onCaptureVolumeIndication(int totalVolume, int cpt, int micVolume) {

    }

    @Override
    public void onAudioQuality(String uid, int quality, short delay, short lost) {

    }

    @Override
    public void onConnectionLost() {

    }

    @Override
    public void onConnectionInterrupted() {

    }

    @Override
    public void onAudioRouteChanged(int routing) {

    }

    @Override
    public void onAudioPlayData(byte[] data, long cpt, long pts, String uid, long duration) {

    }

    @Override
    public void onAudioPlaySpectrumData(byte[] data) {

    }

    @Override
    public void onAudioCapturePcmData(byte[] data, int dataSize, int sampleRate, int channel) {

    }

    @Override
    public void onAudioRenderPcmData(byte[] data, int dataSize, long duration, int sampleRate,
                                     int channel) {

    }

    @Override
    public void onRecvUserAppMsgData(byte[] data, String uid) {

    }

    @Override
    public void onSendAppMsgDataFailedStatus(int status) {

    }

    @Override
    public void onRemoteAudioStopped(String uid, boolean stop) {

    }

    @Override
    public void onRemoteVideoStopped(String uid, boolean stop) {

    }

    @Override
    public void onRemoteVideoPlay(String uid, int width, int height, int elapsed) {

    }

    @Override
    public void onVideoSizeChanged(String uid, int width, int height, int rotation) {

    }

    @Override
    public void onFirstLocalAudioFrameSent(int elapsed) {

    }

    @Override
    public void onFirstLocalVideoFrameSent(int elapsed) {

    }

    @Override
    public void onPublishStreamToCDNStatus(String url, int errorCode) {

    }

    @Override
    public void onNetworkTypeChanged(int type) {

    }

    @Override
    public void onConnectionStatus(int status) {

    }

    @Override
    public void onAudioCaptureStatus(int status) {

    }

    @Override
    public void onVideoCaptureStatus(int status) {

    }
}
