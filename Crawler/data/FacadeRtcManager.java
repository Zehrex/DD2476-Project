2
https://raw.githubusercontent.com/Aivacom/JLYLiveChat-android/master/app/src/main/java/com/mediaroom/facade/FacadeRtcManager.java
package com.mediaroom.facade;


import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.mediaroom.bean.RepToken;
import com.mediaroom.bean.UserMode;
import com.mediaroom.utils.Constant;
import com.mediaroom.utils.LogUtil;
import com.mediaroom.utils.Utils;
import com.thunder.livesdk.IThunderLogCallback;
import com.thunder.livesdk.LiveTranscoding;
import com.thunder.livesdk.ThunderEngine;
import com.thunder.livesdk.ThunderEventHandler;
import com.thunder.livesdk.ThunderNotification;
import com.thunder.livesdk.ThunderRtcConstant;
import com.thunder.livesdk.ThunderVideoCanvas;
import com.thunder.livesdk.ThunderVideoEncoderConfiguration;
import com.thunder.livesdk.video.ThunderPlayerView;
import com.thunder.livesdk.video.ThunderPreviewView;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.thunder.livesdk.ThunderRtcConstant.ThunderVideoRenderMode.THUNDER_RENDER_MODE_CLIP_TO_BOUNDS;
import static com.thunder.livesdk.ThunderRtcConstant.ThunderVideoViewScaleMode.THUNDERVIDEOVIEW_SCALE_MODE_ASPECT_FIT;

public class FacadeRtcManager {
    public static final String TAG = "FacadeRtcManager";
    private static FacadeRtcManager instance;
    private ThunderEngine thunderEngine = null;
    private Observer observer;
    private JoinThunderRoomObserver joinThunderRoomObserver = null;
    private Context context;

    private FacadeRtcManager() {
    }

    public static FacadeRtcManager getInstance() {
        if (instance == null) {
            instance = new FacadeRtcManager();
        }
        return instance;
    }

    public FacadeRtcManager initializeRtc(Context context, String appId, long sceneId, ThunderEventHandler handler) {
        this.context = context;
        if (thunderEngine != null) {
            return this;
        }
        thunderEngine = ThunderEngine.createEngine(context, appId, sceneId, handler);
        return this;
    }

    public void setRoomConfig(int mediaMode, int roomMode) {
        //设置频道属性
        //Set media mode and room scenario mode
        thunderEngine.setMediaMode(mediaMode);
        thunderEngine.setRoomMode(roomMode);
    }

    public void startPush(ThunderPreviewView mPreviewContainer, String uid, int liveMode) {

        setVideoEncoderConfig(liveMode);
        //设置本地预览视图
        //Set local view
        ThunderVideoCanvas mPreviewView = new ThunderVideoCanvas(mPreviewContainer, THUNDERVIDEOVIEW_SCALE_MODE_ASPECT_FIT, uid);
        thunderEngine.setLocalVideoCanvas(mPreviewView);
        //设置视频模式
        //Set local view display mode
        thunderEngine.setLocalCanvasScaleMode(THUNDER_RENDER_MODE_CLIP_TO_BOUNDS);
        //开启预览
        //enable video preview
        thunderEngine.startVideoPreview();
        //开播 音频
        //enabling sending of local audio
        thunderEngine.stopLocalAudioStream(false);
        //开播 视频
        //enable sending of local video stream
        thunderEngine.stopLocalVideoStream(false);
    }

    public void setVideoEncoderConfig(int publishMode) {
        ThunderVideoEncoderConfiguration vConfig = new ThunderVideoEncoderConfiguration();
        vConfig.publishMode = publishMode;
        vConfig.playType = ThunderRtcConstant.ThunderPublishPlayType.THUNDERPUBLISH_PLAY_INTERACT;
        thunderEngine.setVideoEncoderConfig(vConfig);
    }

    public void stopPush() {
        thunderEngine.stopLocalVideoStream(true);
        thunderEngine.stopLocalAudioStream(true);
        thunderEngine.stopVideoPreview();
    }


    //FIXME 当远端流停，不处理View。sdk内部已处理，外部可以不进行判断
    public void prepareRemoteView(ThunderPlayerView mplayerViewContainer, String remoteUid) {
        ThunderVideoCanvas mPlayView = new ThunderVideoCanvas(mplayerViewContainer, THUNDERVIDEOVIEW_SCALE_MODE_ASPECT_FIT, remoteUid);
        thunderEngine.setRemoteVideoCanvas(mPlayView);
        int code = thunderEngine.setRemoteCanvasScaleMode(remoteUid, THUNDER_RENDER_MODE_CLIP_TO_BOUNDS);
        Log.d(TAG, "prepareRemoteView: " + code);
    }

    public void setLogCallback(IThunderLogCallback callback) {
        thunderEngine.setLogCallback(callback);
    }

    public void switchFrontCamera(boolean bFront) {
        thunderEngine.switchFrontCamera(bFront);
    }


    public void joinRoom(byte[] token, String channelName, String uid) {
        int ret = thunderEngine.joinRoom(token, channelName, uid);//进频道成功 0
    }

    public void stopLocalAudioStream(boolean isStop) {
        thunderEngine.stopLocalAudioStream(isStop);
    }

    public void addSubscribe(String roomId, String uid) {
        if (thunderEngine != null) {
            thunderEngine.addSubscribe(roomId, uid);
        }
    }

    public void removeSubscribe(String roomId, String uid) {
        if (thunderEngine != null) {
            thunderEngine.removeSubscribe(roomId, uid);
        }
    }

    public void leaveRoom() {
        thunderEngine.leaveRoom();
    }

    public void setLiveTranscodingTask(String taskId, LiveTranscoding transcoding) {
        if (thunderEngine != null) {
            thunderEngine.setLiveTranscodingTask(taskId, transcoding);
        }
    }

    public void addPublishTranscodingStreamUrl(String taskId, String url) {
        if (thunderEngine != null) {
            thunderEngine.addPublishTranscodingStreamUrl(taskId, url);
        }
    }

    public void removePublishTranscodingStreamUrl(String taskId, String url) {
        if (thunderEngine != null) {
            thunderEngine.removePublishTranscodingStreamUrl(taskId, url);
        }
    }

    public FacadeRtcManager setLogFilePath(String filePath) {
        if (thunderEngine != null) {
            thunderEngine.setLogFilePath(filePath);
        }
        return this;
    }

    public ThunderEventHandler getThunderEventHandler() {
        return thunderEventHandler;
    }

    private ThunderEventHandler thunderEventHandler = new ThunderEventHandler() {

        @Override
        public void onError(int error) {
            LogUtil.e("sdk call", "onError: ");
        }

        @Override
        public void onJoinRoomSuccess(String room, String uid, int elapsed) {
            LogUtil.d(TAG, "onJoinRoomSuccess: " + room + "::::uid =" + uid + "::::elapsed =" + elapsed);
            if (FacadeRtcManager.getInstance().getObserver() != null) {
                FacadeRtcManager.getInstance().getObserver().onJoinRoomSuccess(room, uid, elapsed);
            }
            if (FacadeRtcManager.getInstance().getJoinThunderRoomObserver() != null) {
                FacadeRtcManager.getInstance().getJoinThunderRoomObserver().onJoinRoomSuccess(room, uid, elapsed);
            }
        }

        @Override
        public void onLeaveRoom(ThunderEventHandler.RoomStats status) {
            LogUtil.d(TAG, "onLeaveRoom: " + status.temp);
            if (FacadeRtcManager.getInstance().getObserver() != null) {
                FacadeRtcManager.getInstance().getObserver().onLeaveRoom(status);
            }
        }

        @Override
        public void onBizAuthResult(boolean bPublish, int result) {
            LogUtil.d(TAG, "onBizAuthResult: bPublish = " + bPublish + "::::result =" + result);
        }

        @Override
        public void onSdkAuthResult(int result) {
            LogUtil.d(TAG, "onSdkAuthResult: result = " + result);
        }

        @Override
        public void onUserBanned(boolean status) {
            LogUtil.d(TAG, "onUserBanned: status = " + status);
        }

        @Override
        public void onUserJoined(String uid, int elapsed) {
            LogUtil.d(TAG, "onUserJoined: uid = " + uid + "::::elapsed =" + elapsed);
        }

        @Override
        public void onUserOffline(String uid, int reason) {
            LogUtil.d(TAG, "onUserOffline: uid = " + uid + "::::reason =" + reason);
        }

        @Override
        public void onTokenWillExpire(byte[] token) {
            reFreshToken();
            LogUtil.d(TAG, "onTokenWillExpire: token = " + token);
        }

        @Override
        public void onTokenRequested() {
            reFreshToken();
            LogUtil.d(TAG, "onTokenRequested: ");
        }

        @Override
        public void onNetworkQuality(String uid, int txQuality, int rxQuality) {
            LogUtil.d(TAG, "onNetworkQuality: uid = " + uid + ":::txQuality=" + txQuality + ":::rxQuality=" + rxQuality);
            if (FacadeRtcManager.getInstance().getObserver() != null) {
                FacadeRtcManager.getInstance().getObserver().onNetworkQuality(uid, txQuality, rxQuality);
            }
        }

        @Override
        public void onRoomStats(ThunderNotification.RoomStats stats) {
            LogUtil.d(TAG, "onRoomStats: " + stats.txBitrate);
            if (FacadeRtcManager.getInstance().getObserver() != null) {
                FacadeRtcManager.getInstance().getObserver().onRoomStats(stats);
            }
        }

        //播放音量回调
        @Override
        public void onPlayVolumeIndication(ThunderEventHandler.AudioVolumeInfo[] speakers,
                                           int totalVolume) {
            LogUtil.d(TAG, "onPlayVolumeIndication: totalVolume = " + totalVolume);
        }

        @Override
        public void onCaptureVolumeIndication(int totalVolume, int cpt, int micVolume) {

        }

        @Override
        public void onAudioQuality(String uid, int quality, short delay, short lost) {
            LogUtil.d(TAG, "onAudioQuality: " + uid);
        }

        @Override
        public void onConnectionLost() {

        }

        @Override
        public void onConnectionInterrupted() {

        }

        @Override
        public void onAudioRouteChanged(int routing) {
            LogUtil.d(TAG, "onAudioRouteChanged: ");
        }

        @Override
        public void onAudioPlayData(byte[] data, long cpt, long pts, String uid, long duration) {
            LogUtil.d(TAG, "onAudioPlayData: " + uid);
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
        public void onFirstLocalAudioFrameSent(int elapsed) {

        }

        @Override
        public void onFirstLocalVideoFrameSent(int elapsed) {

        }

        @Override
        public void onPublishStreamToCDNStatus(String url, int errorCode) {
            LogUtil.d(TAG, "onPublishStreamToCDNStatus: " + url + ":::" + errorCode);
            if (FacadeRtcManager.getInstance().getObserver() != null) {
                FacadeRtcManager.getInstance().getObserver().onPublishStreamToCDNStatus(url, errorCode);
            }
        }

        @Override
        public void onNetworkTypeChanged(int type) {

        }

        @Override
        public void onConnectionStatus(int status) {
            LogUtil.d(TAG, "onConnectionStatus: ");
        }


        @Override
        public void onRemoteAudioStopped(String uid, boolean muted) {
            LogUtil.d(TAG, "onRemoteAudioStopped: " + uid + ":::" + muted);
            if (FacadeRtcManager.getInstance().getObserver() != null) {
                FacadeRtcManager.getInstance().getObserver().onRemoteAudioStopped(uid, muted);
            }
        }

        @Override
        public void onRemoteVideoStopped(String uid, boolean muted) {
            LogUtil.d(TAG, "onRemoteVideoStopped: " + uid + ":::" + muted + "Observer: " + FacadeRtcManager.getInstance().getObserver());
            if (FacadeRtcManager.getInstance().getObserver() != null) {
                FacadeRtcManager.getInstance().getObserver().onRemoteVideoStopped(uid, muted);
            }
        }

        @Override
        public void onRemoteVideoPlay(String uid, int width, int height, int elapsed) {
            LogUtil.d(TAG, "onRemoteVideoPlay: " + uid);
        }


        @Override
        public void onVideoSizeChanged(String uid, int width, int height, int rotation) {
            LogUtil.d(TAG, "onVideoSizeChanged: " + uid);
            if (FacadeRtcManager.getInstance().getObserver() != null) {
                FacadeRtcManager.getInstance().getObserver().onVideoSizeChanged(uid, width, height, rotation);
            }
        }
    };

    private void reFreshToken() {
        Utils.getToken(Constant.uid, String.valueOf(Constant.mAppId), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                RepToken repToken = JSON.parseObject(response.body().string(), RepToken.class);
                if (repToken.getObject() != null) {
                    thunderEngine.updateToken(repToken.getObject().getBytes());
                }
            }
        });
    }

    public IThunderLogCallback getThunderLogCallback() {
        return thunderLogCallback;
    }

    private IThunderLogCallback thunderLogCallback = (level, tag, msg) -> {
        switch (level) {
            case ThunderRtcConstant.ThunderLogLevel.THUNDERLOG_LEVEL_TRACE:
                LogUtil.v(tag, msg);
                break;
            case ThunderRtcConstant.ThunderLogLevel.THUNDERLOG_LEVEL_DEBUG:
                LogUtil.d(tag, msg);
                break;
            case ThunderRtcConstant.ThunderLogLevel.THUNDERLOG_LEVEL_WARN:
                LogUtil.w(tag, msg);
                break;
            case ThunderRtcConstant.ThunderLogLevel.THUNDERLOG_LEVEL_ERROR:
                LogUtil.e(tag, msg);
                break;
            case ThunderRtcConstant.ThunderLogLevel.THUNDERLOG_LEVEL_INFO:
            default:
                LogUtil.i(tag, msg);
                break;
        }
    };

    public Observer getObserver() {
        return observer;
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
    }


    public abstract static class Observer {
        public void onJoinRoomSuccess(String channel, String uid, int elapsed) {
        }

        public void onVideoSizeChanged(String uid, int width, int height, int rotation) {
        }

        public void onRemoteVideoStopped(String uid, boolean muted) {
        }

        public void onRemoteAudioStopped(String uid, boolean muted) {
        }

        public void onLeaveRoom(ThunderEventHandler.RoomStats status) {
        }

        public void onPublishStreamToCDNStatus(String url, int errorCode) {
        }

        public void onNetworkQuality(String uid, int txQuality, int rxQuality) {
        }

        public void onRoomStats(ThunderNotification.RoomStats stats) {
        }
    }

    public JoinThunderRoomObserver getJoinThunderRoomObserver() {
        return joinThunderRoomObserver;
    }

    public void setJoinThunderRoomObserver(JoinThunderRoomObserver joinThunderRoomObserver) {
        this.joinThunderRoomObserver = joinThunderRoomObserver;
    }

    public abstract static class JoinThunderRoomObserver {
        public void onJoinRoomSuccess(String room, String uid, int elapsed) {
        }
    }


    public void initLiveMode(List<UserMode> userLiveModes) {
        addLiveUsers("流畅", ThunderRtcConstant.ThunderPublishVideoMode.THUNDERPUBLISH_VIDEO_MODE_FLUENCY, userLiveModes);
        addLiveUsers("标清", ThunderRtcConstant.ThunderPublishVideoMode.THUNDERPUBLISH_VIDEO_MODE_NORMAL, userLiveModes);
        addLiveUsers("高清", ThunderRtcConstant.ThunderPublishVideoMode.THUNDERPUBLISH_VIDEO_MODE_HIGHQULITY, userLiveModes);
    }

    private void addLiveUsers(String mode, int thunderpublishVideoMode, List<UserMode> userLiveModes) {
        UserMode userMode = new UserMode();
        userMode.setModeTip(mode);
        userMode.setModeType(Constant.LIVE_MODETYPE);
        userMode.setThunderMode(thunderpublishVideoMode);
        userLiveModes.add(userMode);
    }
}
