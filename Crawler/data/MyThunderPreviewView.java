2
https://raw.githubusercontent.com/Aivacom/JLYMeet-android/master/app/src/main/java/com/mediaroom/ui/MyThunderPreviewView.java
package com.mediaroom.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mediaroom.R;
import com.mediaroom.facade.OnRemoteListener;
import com.mediaroom.facade.RoomManager;
import com.thunder.livesdk.video.ThunderPreviewView;

/**
 *
 * Custom Local video view with some state
 *
 * ZH:
 * 自定义本地视频视图，增加一些状态
 *
 * @author Aslan chenhengfei@yy.com
 * @since 2019/12/26
 */
public class MyThunderPreviewView extends RelativeLayout implements OnRemoteListener {

    private ThunderPreviewView mThunderPlayerView;
    private TextView tvNetStatus;
    private TextView tvUID;
    private ImageView ivAudio;

    private String UID;
    private boolean isInRoom = false;

    public boolean isInRoom() {
        return isInRoom;
    }

    public MyThunderPreviewView(Context context) {
        super(context);
        ini(context, null, 0);
    }

    public MyThunderPreviewView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ini(context, attrs, 0);
    }

    public MyThunderPreviewView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ini(context, attrs, defStyleAttr);
    }

    private void ini(Context context, AttributeSet attrs, int defStyleAttr) {
        LayoutInflater.from(context).inflate(R.layout.layout_thunder_local, this);

        mThunderPlayerView = findViewById(R.id.view);
        tvNetStatus = findViewById(R.id.tvNetStatus);
        ivAudio = findViewById(R.id.ivAudio);
        tvUID = findViewById(R.id.tvUID);
    }

    public ThunderPreviewView getThunderPlayerView() {
        return mThunderPlayerView;
    }

    /**
     * Set up network poor display
     *
     * ZH:
     * 设置网络差显示
     */
    public void changeLowNetStatus(boolean isShown) {
        if (isShown) {
            tvNetStatus.setVisibility(VISIBLE);
        } else {
            tvNetStatus.setVisibility(GONE);
        }
    }

    /**
     * Join the room
     *
     * ZH:
     * 加入房间
     */
    public void joinRoom(String UID) {
        if (TextUtils.equals(this.UID, UID)) {
            return;
        }

        this.UID = UID;
        tvUID.setText(UID);
        tvNetStatus.setVisibility(GONE);
        RoomManager.getInstance(getContext()).setLocalVideoCanvas(mThunderPlayerView, UID);
        isInRoom = true;
    }

    /**
     * Leave the room
     *
     * ZH:
     * 离开房间
     */
    public void leaveRoom() {
        this.UID = null;
        tvUID.setText("");
        tvNetStatus.setVisibility(GONE);

        mThunderPlayerView.clearViews();
        mThunderPlayerView.addViews(mThunderPlayerView.getSurfaceView());
        isInRoom = false;
    }

    /**
     * Don't keep the last frame
     *
     * ZH:
     * 不要保留最后一帧
     */
    public void resetView() {
        mThunderPlayerView.clearViews();
        mThunderPlayerView.addViews(mThunderPlayerView.getSurfaceView());
    }

    /**
     * Mute the audio
     *
     * ZH:
     * 静音
     */
    public void setAudioMute() {
        ivAudio.setVisibility(View.VISIBLE);
        ivAudio.setImageResource(R.mipmap.img_audiostop3x);
    }

    /**
     * Set volume display
     *
     * ZH:
     * 设置音量显示
     */
    public void setAudioVolume(Integer micVolume) {
        if (micVolume == null) {
            ivAudio.setVisibility(View.GONE);
        } else {
            ivAudio.setVisibility(View.VISIBLE);
            if (micVolume <= 30) {
                ivAudio.setImageResource(R.mipmap.img_audiovolume_level1);
            } else if (micVolume <= 60) {
                ivAudio.setImageResource(R.mipmap.img_audiovolume_level2);
            } else {
                ivAudio.setImageResource(R.mipmap.img_audiovolume_level3);
            }
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        MainActivity.mRemoteListeners.add(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        MainActivity.mRemoteListeners.remove(this);
    }

    @Override
    public void onJoinRoom(String uid) {

    }

    @Override
    public void onLeaveRoom(String uid) {
        if (TextUtils.equals(this.UID, uid)) {
            leaveRoom();
        }
    }
}
