2
https://raw.githubusercontent.com/Aivacom/JLYLiveChat-android/master/app/src/main/java/com/mediaroom/ui/ChatRoomActivity.java
package com.mediaroom.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.Group;
import android.support.constraint.Placeholder;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.hummer.im.Error;
import com.hummer.im.HMR;
import com.hummer.im.chatroom.Challenges;
import com.hummer.im.chatroom.ChatRoomInfo;
import com.hummer.im.chatroom.ChatRoomService;
import com.hummer.im.model.chat.Message;
import com.hummer.im.model.chat.contents.Text;
import com.hummer.im.model.id.ChatRoom;
import com.hummer.im.model.id.User;
import com.hummer.im.service.ChannelStateService;
import com.hummer.im.service.ChatService;
import com.mediaroom.MyApplication;
import com.mediaroom.R;
import com.mediaroom.adapter.ChatMessageAdapter;
import com.mediaroom.adapter.ModeAdapter;
import com.mediaroom.bean.ChatMessage;
import com.mediaroom.bean.UserMode;
import com.mediaroom.facade.FacadeRtcManager;
import com.mediaroom.facade.HummerManager;
import com.mediaroom.utils.BaseActivity;
import com.mediaroom.utils.ConfirmDialog;
import com.mediaroom.utils.Constant;
import com.mediaroom.utils.CustomPopWindow;
import com.mediaroom.utils.SoftKeyBoardListener;
import com.thunder.livesdk.ThunderEventHandler;
import com.thunder.livesdk.ThunderRtcConstant;
import com.thunder.livesdk.video.ThunderPlayerView;
import com.thunder.livesdk.video.ThunderPreviewView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.mediaroom.utils.Constant.LIVE_CONNECT_APPLY;
import static com.mediaroom.utils.Constant.LIVE_CONNECT_CANCEL;


public class ChatRoomActivity extends BaseActivity implements View.OnClickListener, ChatRoomService.MemberListener, ChatRoomService.ChatRoomListener, ChannelStateService.ChannelStateListener {
    private ImageView mIvSendMessage, mIvCloseRoom, mIvChat, mIvFeedBack, mImgSwitchCamera, mImgMicrophone;
    private String roomId;
    private View mVBgBottom;
    private EditText mEtMessage;
    private Group mGroupBottom, mGroupBottomChat, mGroupOwner, mGroupLive, mGroupVideo;

    private ChatMessageAdapter adapter;
    private ModeAdapter modeAdapter;
    private View contentView;
    private RecyclerView recyclerView;
    private RecyclerView modeRecyclerView;
    private TextView popTvStopCancel, popTvStopHead;
    private CustomPopWindow mCustomPopWindow;
    private ConfirmDialog confirmDialog;

    private TextView mTvChatRoomMember, mTvChatrRoomid, mTvLiveModeSelector;
    private Placeholder mPhRecyclerViewChat;
    private TextView mLocalViewNet, mRemoteViewNet, mTVLocalUid, mTvRemoteUid, mTvHandleLive, mTvSubscribeSelector;
    private ThunderPreviewView mLocalView;
    private ThunderPlayerView mRemoteView;
    private String remoteLiveRoomId;
    private ConstraintLayout cl;
    private ConstraintSet constraintSet = new ConstraintSet();
    private User owner;
    private ChatRoom mChatRoom;
    private long mRemoteUid;
    private int liveMode = ThunderRtcConstant.ThunderPublishVideoMode.THUNDERPUBLISH_VIDEO_MODE_NORMAL;//默认开播档位标清
    private List<UserMode> userLiveModes = new ArrayList<>();
    private List<UserMode> userMembers = new ArrayList<>();

    private boolean bFront = false;
    private boolean isStopMicrophone = false;
    private boolean isChatMuted = false;
    private boolean isSubscribeRemote = false;
    private boolean isLive;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_chatroom;
    }

    @SuppressLint("ResourceType")
    @Override
    protected void initView() {
        cl = findViewById(R.id.cl_chatroom);
        mEtMessage = findViewById(R.id.et_message);
        mIvSendMessage = findViewById(R.id.iv_send);
        mIvCloseRoom = findViewById(R.id.iv_closeroom);
        mIvChat = findViewById(R.id.iv_chat);
        mIvFeedBack = findViewById(R.id.iv_feedback);
        mTvChatRoomMember = findViewById(R.id.tv_chatroom_members);
        mPhRecyclerViewChat = findViewById(R.id.ph_recyclerView_chat);
        mTvHandleLive = findViewById(R.id.tv_handle_live);
        mTvChatrRoomid = findViewById(R.id.tv_chatroomid);
        mTvLiveModeSelector = findViewById(R.id.tv_livemode_selector);
        mTvSubscribeSelector = findViewById(R.id.tv_subscribe_selector);
        mImgSwitchCamera = findViewById(R.id.img_switchcamera);
        mImgMicrophone = findViewById(R.id.iv_microphone);
        mVBgBottom = findViewById(R.id.bg_bottom);
        recyclerView = findViewById(R.id.recyclerView_chat);

        mGroupBottom = findViewById(R.id.group_bottom);
        mGroupBottomChat = findViewById(R.id.group_bottom_chat);
        mGroupOwner = findViewById(R.id.group_owner);
        mGroupLive = findViewById(R.id.group_live);
        mGroupVideo = findViewById(R.id.group_video);

        mLocalView = findViewById(R.id.local_view);
        mTVLocalUid = findViewById(R.id.tv_local_uid);
        mRemoteView = findViewById(R.id.remote_view);
        mTvRemoteUid = findViewById(R.id.tv_remote_uid);
        mLocalViewNet = findViewById(R.id.local_view_net);
        mRemoteViewNet = findViewById(R.id.remote_view_net);

        mIvCloseRoom.setOnClickListener(this::onClick);
        mIvSendMessage.setOnClickListener(this::onClick);
        mIvChat.setOnClickListener(this::onClick);
        mIvFeedBack.setOnClickListener(this::onClick);
        mTvChatRoomMember.setOnClickListener(this::onClick);
        mTvHandleLive.setOnClickListener(this::onClick);
        mTvSubscribeSelector.setOnClickListener(this::onClick);
        mImgMicrophone.setOnClickListener(this::onClick);
        mImgSwitchCamera.setOnClickListener(this::onClick);
        mTvLiveModeSelector.setOnClickListener(this::onClick);

        adapter = new ChatMessageAdapter(new ArrayList<>(), ChatRoomActivity.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setStackFromEnd(true);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.scrollToPosition(adapter.getItemCount() - 1);//此句为设置显示

        initPopView();
        SoftKeyBoardListener.setListener(ChatRoomActivity.this, new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                keyBoardChangeView(true);
            }

            @Override
            public void keyBoardHide() {
                keyBoardChangeView(false);
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev != null && mEtMessage.getVisibility() != View.GONE) {
            int[] location = {0, 0};
            mVBgBottom.getLocationInWindow(location);
            int left = location[0] + mVBgBottom.getWidth() / 2, bottom = location[1] + mVBgBottom.getHeight();
            switch (ev.getAction()) {
                case MotionEvent.ACTION_UP:
                    if (ev.getRawX() < left
                            || ev.getRawY() > bottom) {
                        return false;
                    }
                    break;
            }
        }
        return super.dispatchTouchEvent(ev);
    }


    private void keyBoardChangeView(boolean isShow) {
        if (!isShow) {
            mVBgBottom.setBackground(getResources().getDrawable(R.color.bg_grey));
            mGroupBottomChat.setVisibility(View.GONE);
            mPhRecyclerViewChat.setContentId(0);
            recyclerView.setAlpha(1.0F);
            mGroupBottom.setVisibility(View.VISIBLE);
            if (isLive) {
                mGroupLive.setVisibility(View.VISIBLE);
            }
            mEtMessage.setVisibility(View.GONE);
        } else {
            mVBgBottom.setBackground(getResources().getDrawable(R.color.white95));
            mGroupBottomChat.setVisibility(View.VISIBLE);
            mPhRecyclerViewChat.setContentId(recyclerView.getId());
            recyclerView.setAlpha(0.5F);
            mGroupBottom.setVisibility(View.GONE);
            mGroupLive.setVisibility(View.GONE);
            mEtMessage.setVisibility(View.VISIBLE);
        }
        recyclerView.scrollToPosition(adapter.getItemCount() - 1);//此句为设置显示
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View v = getWindow().peekDecorView();
        if (null != v) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        roomId = intent.getStringExtra(Constant.ROOMID_KEY);
        mChatRoom = new ChatRoom(Long.parseLong(roomId));
        mTvChatrRoomid.setText("房间号: " + roomId);

        FacadeRtcManager.getInstance().initLiveMode(userLiveModes);
        FacadeRtcManager.getInstance().setObserver(observer);
        checkOwner();

        HMR.getService(ChatService.class).addMessageListener(null, messageListener);
        HMR.getService(ChatRoomService.class).addMemberListener(this);
        HMR.getService(ChatRoomService.class).addListener(this);
        HMR.getService(ChannelStateService.class).addChannelStateListener(this::onUpdateChannelState);
    }


    private void initPopView() {
        contentView = LayoutInflater.from(ChatRoomActivity.this).inflate(R.layout.custorm_pop, null);
        popTvStopCancel = contentView.findViewById(R.id.pop_tv_cancel);
        popTvStopHead = contentView.findViewById(R.id.pop_tv_head);
        modeRecyclerView = contentView.findViewById(R.id.recyclerView_pop);
        popTvStopCancel.setOnClickListener(this::onClick);
        modeAdapter = new ModeAdapter(new ArrayList<>());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        modeRecyclerView.setLayoutManager(layoutManager);
        modeRecyclerView.setAdapter(modeAdapter);
        modeRecyclerView.setItemAnimator(new DefaultItemAnimator());
        modeAdapter.setOnItemClickListener((view, position) -> {
            switch (modeAdapter.getData().get(position).getModeType()) {
                case Constant.LIVE_MODETYPE:
                    mTvLiveModeSelector.setText(modeAdapter.getData().get(position).getModeTip());
                    liveMode = modeAdapter.getData().get(position).getThunderMode();
                    Log.d(TAG, "initPopView:liveMode " + liveMode);
                    FacadeRtcManager.getInstance().setVideoEncoderConfig(liveMode);
                    break;
                case Constant.MEMBERS_MODETYPE:
                    showDialogProgress();
                    mRemoteUid = Long.parseLong(modeAdapter.getData().get(position).getModeTip());
                    HummerManager.getInstance().sendSignalMessage(ChatRoomActivity.this, mChatRoom, LIVE_CONNECT_APPLY, new User(mRemoteUid));
                    break;
            }
            if (mCustomPopWindow != null) {
                mCustomPopWindow.dissmiss();
            }
        });
    }

    private void showPopView(int size) {
        mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(ChatRoomActivity.this)
                .setView(contentView)
                .size(getResources().getDisplayMetrics().widthPixels, popTvStopCancel.getLayoutParams().height * (size + 1) + 10)//动态显示所有item数据，popwindow高度为:item + 1
                .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                .setBgDarkAlpha(0.7f) // 控制亮度
                .setFocusable(true)//是否获取焦点，默认为ture
                .setOutsideTouchable(true)//是否PopupWindow 以外触摸dissmiss
                .create()
                .showAtLocation(contentView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 10);

    }

    private void checkOwner() {
        showDialogProgress();
        HMR.getService(ChatRoomService.class)
                .fetchRoleMembers(mChatRoom, false, new HMR.CompletionArg<Map<String, List<User>>>() {
                    @Override
                    public void onSuccess(Map<String, List<User>> arg) {
                        Log.d(TAG, "onSuccess: " + JSON.toJSONString(arg));
                        owner = arg.get("owner").get(0);
                        mRemoteUid = owner.getId();
                        HummerManager.isOwner = HMR.getMe().getId() == owner.getId() ? true : false;
                        if (!HummerManager.isOwner) {
                            //观众状态,远端用户默认设置为房主uid
                            HummerManager.getInstance().isOwnerOnLine(mChatRoom, String.valueOf(owner.getId()));
                            //观众默认订阅房主
                            FacadeRtcManager.getInstance().addSubscribe(roomId + owner.getId(), String.valueOf(owner.getId()));
                            checkChatMuted();
                        } else {
                            HummerManager.isOwnerOnLine = true;
                            //房主动态修改约束条件
                            constraintSet.clone(ChatRoomActivity.this, R.layout.activity_chatroom);
                            constraintSet.connect(mLocalView.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT);
                            constraintSet.connect(mLocalView.getId(), ConstraintSet.RIGHT, mRemoteView.getId(), ConstraintSet.LEFT);
                            constraintSet.connect(mRemoteView.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT);
                            constraintSet.connect(mRemoteView.getId(), ConstraintSet.LEFT, mLocalView.getId(), ConstraintSet.RIGHT);
                            constraintSet.applyTo(cl);

                            //Group在使用后，会对它所管理的所有view的显示进行重新赋值，重新设置它所管理的ID显示效果
                            mGroupOwner.setVisibility(View.VISIBLE);
                            mGroupVideo.setVisibility(View.VISIBLE);
                            dissMissDialogProgress();
                        }
                    }

                    @Override
                    public void onFailed(Error err) {
                        dissMissDialogProgress();
                    }
                });
    }

    private void checkChatMuted() {
        HMR.getService(ChatRoomService.class).
                isMuted(mChatRoom, new User(HMR.getMe().getId()), new HMR.CompletionArg<Boolean>() {
                    @Override
                    public void onSuccess(Boolean arg) {
                        if (arg != null) {
                            isChatMuted = arg;
                        }
                        dissMissDialogProgress();
                    }

                    @Override
                    public void onFailed(Error err) {
                        dissMissDialogProgress();
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_switchcamera:
                switchFrontCamera();
                break;
            case R.id.iv_microphone:
                switchMicrophone();
                break;
            case R.id.tv_handle_live:
                switchVideoLive();
                break;
            case R.id.tv_subscribe_selector:
                selectrorSubscribe();
                break;
            case R.id.iv_feedback:
                startActivity(new Intent(ChatRoomActivity.this, FeedBackActivity.class));
                break;
            case R.id.iv_chat:
                keyBoardChangeView(true);
                openKeyBoard(mEtMessage);
                break;
            case R.id.iv_closeroom:
                leveRoom();
                break;
            case R.id.tv_livemode_selector:
                modeAdapter.resetData(userLiveModes);
                popViewStatus(true);
                showPopView(userLiveModes.size());
                break;
            case R.id.tv_chatroom_members:
                toMembersActivity();
                break;
            case R.id.pop_tv_cancel:
                if (mCustomPopWindow != null) {
                    mCustomPopWindow.dissmiss();
                }
                break;
            case R.id.iv_send:
                if (!this.isChatMuted) {
                    sendMessage(mEtMessage.getText().toString(), String.valueOf(HMR.getMe().getId()));
                    hideKeyboard();
                } else {
                    Toast.makeText(this, "您已被房主禁言", Toast.LENGTH_SHORT).show();
                    hideKeyboard();
                }
                break;
        }
    }

    private void toMembersActivity() {
        Intent intent = new Intent(ChatRoomActivity.this, MemberListActivity.class);
        intent.putExtra(Constant.ROOMID_KEY, roomId);
        intent.putExtra(Constant.ROOMOWNERID_KEY, owner.getId());
        intent.putExtra(Constant.ROOMISLIVE_KEY, isLive);
        intent.putExtra(Constant.ROOMISSUBCRIBE_KEY, isSubscribeRemote);
        intent.putExtra(Constant.ROOMISSUBCRIBE_MREMOTEUID_KEY, mRemoteUid);
        startActivity(intent);
    }

    private void selectrorSubscribe() {
        if (!isSubscribeRemote) {
            showDialogProgress();
            HMR.getService(ChatRoomService.class).fetchMembers(mChatRoom, 100, 0, new HMR.CompletionArg<List<User>>() {
                @Override
                public void onSuccess(List<User> users) {
                    userMembers.clear();
                    for (int i = 0; i < users.size(); i++) {
                        if (users.get(i).getId() != HMR.getMe().getId()) {
                            UserMode userMode = new UserMode();
                            userMode.setModeTip(String.valueOf(users.get(i).getId()));
                            userMode.setModeType(Constant.MEMBERS_MODETYPE);
                            userMembers.add(userMode);
                        }
                    }
                    if (!userMembers.isEmpty()) {
                        modeAdapter.resetData(userMembers);
                        popViewStatus(false);
                        showPopView(userMembers.size());
                    } else {
                        Toast.makeText(ChatRoomActivity.this, "房间内暂无其他人", Toast.LENGTH_SHORT).show();
                    }
                    dissMissDialogProgress();
                }

                @Override
                public void onFailed(Error err) {
                    dissMissDialogProgress();
                }
            });
        } else {
            HummerManager.getInstance().sendSignalMessage(ChatRoomActivity.this, mChatRoom, LIVE_CONNECT_CANCEL, new User(mRemoteUid));
            switchSubscribe();
        }
    }

    private void popViewStatus(boolean isMode) {
        popTvStopHead.setVisibility(isMode ? View.GONE : View.VISIBLE);
        popTvStopCancel.setVisibility(isMode ? View.VISIBLE : View.GONE);
    }

    private void switchSubscribe() {
        isSubscribeRemote = !isSubscribeRemote;
        remoteLiveRoomId = roomId + mRemoteUid;
        if (isSubscribeRemote) {
            mTvSubscribeSelector.setText("取消订阅");
            mTvSubscribeSelector.setCompoundDrawables(null, null, null, null);
            mTvSubscribeSelector.setGravity(Gravity.CENTER);
            FacadeRtcManager.getInstance().addSubscribe(remoteLiveRoomId, String.valueOf(mRemoteUid));
        } else {
            mTvSubscribeSelector.setText("订阅");
            Drawable drawable = MyApplication.getInstance().getResources().getDrawable(R.drawable.ic_pull);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            mTvSubscribeSelector.setCompoundDrawables(null, null, drawable, null);
            mRemoteView.setVisibility(View.GONE);
            mTvRemoteUid.setVisibility(View.GONE);
            mRemoteViewNet.setVisibility(View.GONE);
            FacadeRtcManager.getInstance().removeSubscribe(remoteLiveRoomId, String.valueOf(mRemoteUid));
        }
    }

    private void switchVideoLive() {
        isLive = !isLive;
        if (isLive) {
            FacadeRtcManager.getInstance().startPush(mLocalView, String.valueOf(HMR.getMe().getId()), liveMode);
            mTvHandleLive.setText("关闭直播");
            mTVLocalUid.setText(String.valueOf(HMR.getMe().getId()));
            mLocalView.setVisibility(View.VISIBLE);
            mTVLocalUid.setVisibility(View.VISIBLE);
            mLocalView.getSurfaceView().setVisibility(View.VISIBLE);
            mGroupLive.setVisibility(View.VISIBLE);
            mGroupVideo.setVisibility(View.VISIBLE);
        } else {
            if (isStopMicrophone) {
                switchMicrophone();
            }
            FacadeRtcManager.getInstance().stopPush();
            mTvHandleLive.setText("开始直播");
            mLocalView.setVisibility(View.GONE);
            mTVLocalUid.setVisibility(View.GONE);
            mLocalView.getSurfaceView().setVisibility(View.GONE);
            mGroupLive.setVisibility(View.GONE);
            if (!HummerManager.isOwner) {
                mGroupVideo.setVisibility(View.GONE);
            }
            mLocalViewNet.setVisibility(View.GONE);
        }
    }

    private void leveRoom() {
        if (isLive) {
            Toast.makeText(this, "请关闭直播再退出房间", Toast.LENGTH_SHORT).show();
            return;
        }
        if (isSubscribeRemote) {
            Toast.makeText(this, "请取消订阅再退出房间", Toast.LENGTH_SHORT).show();
            return;
        }
        showDialogProgress();
        HMR.getService(ChatRoomService.class).leave(mChatRoom, new HMR.Completion() {
            @Override
            public void onSuccess() {
                leaveThunderRoom();
            }

            @Override
            public void onFailed(Error err) {
                dissMissDialogProgress();
                if (err.code == Error.Code.ResourceNotFound) {
                    Toast.makeText(ChatRoomActivity.this, err.desc, Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void switchFrontCamera() {
        FacadeRtcManager.getInstance().switchFrontCamera(!bFront);
        bFront = !bFront;
    }

    private void switchMicrophone() {
        isStopMicrophone = !isStopMicrophone;
        mImgMicrophone.setImageDrawable(getResources().getDrawable(isStopMicrophone ? R.drawable.img_microphone_close : R.drawable.img_microphone_open));
        FacadeRtcManager.getInstance().stopLocalAudioStream(isStopMicrophone);
    }

    @Override
    public void onBasicInfoChanged(@NonNull ChatRoom chatRoom, @NonNull Map<ChatRoomInfo.BasicInfoType, String> propInfo) {

    }

    @Override
    public void onChatRoomDismissed(@NonNull ChatRoom chatRoom, @NonNull User member) {
        if (Long.parseLong(roomId) == chatRoom.getId()) {
            if (!HummerManager.isOwner) {//当房主自己销毁，过滤
                HummerManager.closeChatRoom = true;
            }
            ChatRoomActivity.this.finish();
        }
    }

    @Override
    public void onMemberJoined(@NonNull ChatRoom chatRoom, @NonNull List<User> members) {
        Log.d(TAG, "onMemberJoined: ");
        if (Long.parseLong(roomId) == chatRoom.getId()) {
            checkMember(members, true);
        }
    }

    @Override
    public void onMemberLeaved(@NonNull ChatRoom chatRoom, @NonNull List<User> members, int type, @NonNull String reason) {
        Log.d(TAG, "onMemberLeaved: ");
        if (Long.parseLong(roomId) == chatRoom.getId()) {
            checkMember(members, false);
        }
    }

    @Override
    public void onMemberCountChanged(@NonNull ChatRoom chatRoom, int count) {
        Log.d(TAG, "onMemberCountChanged: ");
    }

    @Override
    public void onRoleAdded(@NonNull ChatRoom chatRoom, @NonNull String role, @NonNull User admin, @NonNull User fellow) {

    }

    @Override
    public void onRoleRemoved(@NonNull ChatRoom chatRoom, @NonNull String role, @NonNull User admin, @NonNull User fellow) {

    }

    @Override
    public void onMemberKicked(@NonNull ChatRoom chatRoom, @NonNull User admin, @NonNull List<User> member, @NonNull String reason) {
        Log.e("TAGGG", admin.getId() + "--------------------ownerID=" + owner.getId() + "---------getMEID" + HMR.getMe().getId());
        if (Long.parseLong(roomId) == chatRoom.getId()) {
            HummerManager.getInstance().onMemberKicked(ChatRoomActivity.this, mChatRoom, admin, member, adapter, recyclerView);
        }
    }


    @Override
    public void onMemberMuted(@NonNull ChatRoom chatRoom, @NonNull User operator, @NonNull Set<User> members, @Nullable String reason) {
        checkMuted(members, true);
    }

    @Override
    public void onMemberUnmuted(@NonNull ChatRoom chatRoom, @NonNull User operator, @NonNull Set<User> members, @Nullable String reason) {
        checkMuted(members, false);
    }

    @Override
    public void onUserInfoSet(@NonNull ChatRoom chatRoom, @NonNull User user, @NonNull Map<String, String> infoMap) {

    }

    @Override
    public void onUserInfoDeleted(@NonNull ChatRoom chatRoom, @NonNull User user, @NonNull Map<String, String> infoMap) {

    }

    private void checkMuted(Set<User> members, boolean isMuted) {
        for (User user : members) {
            if (user.getId() == HMR.getMe().getId()) {
                this.isChatMuted = isMuted;
            }

            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setMsgType(adapter.TYPE_ITEM_CHATMESSAGE_TIP);
            chatMessage.setMessage(user.getId() + (isMuted ? "被禁言" : "恢复发言"));
            adapter.addNewData(chatMessage);
            recyclerView.scrollToPosition(adapter.getItemCount() - 1);//此句为设置显示
        }
    }

    private void checkMember(List<User> members, boolean isJoin) {
        if (members != null && members.size() >= 1) {
            Log.d(TAG, "checkMember: " + JSON.toJSONString(members));
            for (int i = 0; i < members.size(); i++) {
                if (!isJoin && HummerManager.isOwner && members.get(i).getId() == mRemoteUid && isSubscribeRemote) {//连麦用户异常离开房间处理,被踢/被挤下线
                    switchSubscribe();
                }
                if (owner != null && members.get(i).getId() == owner.getId()) {
                    HummerManager.isOwnerOnLine = isJoin;
                    if (!isJoin && !HummerManager.isOwner && isLive) {//连麦用户离开房间观众自动取消连麦
                        switchVideoLive();
                    }
                }

                if (!isJoin && HMR.getMe().getId() == members.get(i).getId()) {//断网重联
                    reJoinChatroom();
                }

                if (HMR.getMe().getId() != members.get(i).getId()) {//不处理本地加入回调信息
                    ChatMessage chatMessage = new ChatMessage();
                    chatMessage.setMsgType(adapter.TYPE_ITEM_CHATMESSAGE_TIP);
                    chatMessage.setMessage(members.get(i).getId() + (isJoin ? "加入" : "离开") + "房间");
                    adapter.addNewData(chatMessage);
                    recyclerView.scrollToPosition(adapter.getItemCount() - 1);//此句为设置显示
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (confirmDialog != null) {
            confirmDialog.dismiss();
        }
        leaveThunderRoom();
        HMR.getService(ChatService.class).removeMessageListener(null, messageListener);
        HMR.getService(ChatRoomService.class).removeMemberListener(this);
        HMR.getService(ChatRoomService.class).removeListener(this);
        HMR.getService(ChannelStateService.class).removeChannelStateListener(this::onUpdateChannelState);
    }

    private void leaveThunderRoom() {
        if (isStopMicrophone) {//恢复麦克风状态
            switchMicrophone();
        }
        if (!bFront) {//默认设置前置摄像头
            switchFrontCamera();
        }
        FacadeRtcManager.getInstance().leaveRoom();
    }


    ChatService.MessageListener messageListener = new ChatService.MessageListener() {
        @Override
        public void beforeSendingMessage(@NonNull Message message) {

        }

        @Override
        public void afterSendingMessage(@NonNull Message message) {

        }

        @Override
        public void beforeReceivingMessage(@NonNull Message message) {

        }


        @Override
        public void afterReceivingMessage(@NonNull Message message) {
            parseMsg(message);
        }
    };

    private void parseMsg(Message message) {
        String content = "";
        long sender = message.getSender().getId();
        Log.d(TAG, "afterReceivingMessage: " + message.getContent().getClass().getCanonicalName());
        if (message.getContent() instanceof Text) {
            content = ((Text) message.getContent()).getText();
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setUid(String.valueOf(sender));
            chatMessage.setMsgType(adapter.TYPE_ITEM_CHATMESSAGE_OTHER);
            chatMessage.setMessage(content);
            adapter.addNewData(chatMessage);
            recyclerView.scrollToPosition(adapter.getItemCount() - 1);//此句为设置显示
        } else if (message.getContent() instanceof ChatRoomService.Signal) {
            content = ((ChatRoomService.Signal) message.getContent()).content;
            switch (content) {
                case Constant.LIVE_CONNECT_APPLY:
                    if (message.getSender().getId() == owner.getId()) {
                        showHandleConfirm("房主邀请您开播", owner);
                    }
                    break;
                case Constant.LIVE_CONNECT_CANCEL:
                    if (message.getSender().getId() == owner.getId()) {
                        Toast.makeText(this, "房主已取消订阅", Toast.LENGTH_SHORT).show();
                        if (isLive) {
                            switchVideoLive();
                        }
                    }
                    break;
                case Constant.LIVE_CONNECT_ACCEPT:
                    if (message.getSender().getId() != owner.getId()) {
                        dissMissDialogProgress();
                        if (!isSubscribeRemote) {
                            switchSubscribe();
                        }
                    }
                    break;
                case Constant.LIVE_CONNECT_REFUSE:
                    if (message.getSender().getId() != owner.getId()) {
                        dissMissDialogProgress();
                        Toast.makeText(this, message.getSender().getId() + "用户拒绝了你的邀请", Toast.LENGTH_SHORT).show();
                    }
                    break;

            }
        }
    }

    private void showHandleConfirm(String des, User user) {
        if (confirmDialog != null && confirmDialog.isShowing()) {
            return;
        }
        confirmDialog = new ConfirmDialog(this, new ConfirmDialog.OnConfirmCallback() {
            @Override
            public void onSure() {
                if (!isLive) {
                    switchVideoLive();
                }
                HummerManager.getInstance().sendSignalMessage(ChatRoomActivity.this, mChatRoom, Constant.LIVE_CONNECT_ACCEPT, user);
                confirmDialog.dismiss();
            }

            @Override
            public void onCancel() {
                HummerManager.getInstance().sendSignalMessage(ChatRoomActivity.this, mChatRoom, Constant.LIVE_CONNECT_REFUSE, user);
            }
        });
        confirmDialog.setTitle("提示");
        confirmDialog.setDesc(des);
        confirmDialog.setButton("拒绝", "同意");
        confirmDialog.show();
    }

    private void sendMessage(String content, String sender) {
        mEtMessage.getText().clear();
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setUid(sender);
        chatMessage.setMsgType(adapter.TYPE_ITEM_CHATMESSAGE_OTHER);
        chatMessage.setMessage(content);
        chatMessage.setSend(true);
        chatMessage.setLoading(true);
        chatMessage.setMuted(this.isChatMuted);
        adapter.addNewData(chatMessage);
        recyclerView.scrollToPosition(adapter.getItemCount() - 1);//此句为设置显示
        int postion = adapter.getItemCount() - 1;
        Message msg = new Message(mChatRoom, new Text(content));
        HMR.getService(ChatService.class).send(msg, new HMR.Completion() {
            @Override
            public void onSuccess() {
                chatMessage.setSend(true);
                chatMessage.setLoading(false);
                adapter.notifyItemChanged(postion, chatMessage);
            }

            @Override
            public void onFailed(Error error) {
                Log.d(TAG, "onFailed:  " + error.toString());
                if (error.code == Error.Code.kForbiddenException) {
                    isChatMuted = true;
                    chatMessage.setMuted(true);
                }
                if (error.code == Error.Code.ResourceNotFound) {
                    Toast.makeText(ChatRoomActivity.this, error.desc, Toast.LENGTH_SHORT).show();
                    finish();
                }
                chatMessage.setSend(false);
                chatMessage.setLoading(false);
                adapter.notifyItemChanged(postion, chatMessage);
            }
        });
    }

    FacadeRtcManager.Observer observer = new FacadeRtcManager.Observer() {

        @Override
        public void onJoinRoomSuccess(String room, String uid, int elapsed) {
            dissMissDialogProgress();
        }

        @Override
        public void onVideoSizeChanged(String uid, int width, int height, int rotation) {

        }

        @Override
        public void onLeaveRoom(ThunderEventHandler.RoomStats status) {
            super.onLeaveRoom(status);
            dissMissDialogProgress();
            ChatRoomActivity.this.finish();
        }

        @Override
        public void onRemoteVideoStopped(String uid, boolean muted) {
            Log.d(TAG, "onRemoteVideoStopped: " + uid + "::" + muted);
            if (mRemoteUid == Long.parseLong(uid)) {
                if (!muted) {
                    FacadeRtcManager.getInstance().prepareRemoteView(mRemoteView, String.valueOf(mRemoteUid));
                    mRemoteView.setVisibility(View.VISIBLE);
                    mTvRemoteUid.setVisibility(View.VISIBLE);
                    mTvRemoteUid.setText(String.valueOf(mRemoteUid));
                } else {
                    if (HummerManager.isOwner) {//仅房主才会自动取消订阅
                        switchSubscribe();
                    } else {
                        mRemoteView.setVisibility(View.GONE);
                        mTvRemoteUid.setVisibility(View.GONE);
                    }
                }
            }
        }

        @Override
        public void onRemoteAudioStopped(String uid, boolean muted) {

        }

        @Override
        public void onNetworkQuality(String uid, int txQuality, int rxQuality) {
            //当前用户下行
            if (uid.equals("0")) {
                if (txQuality == ThunderRtcConstant.NetworkQuality.THUNDER_QUALITY_VBAD) {
                    mLocalViewNet.setVisibility(View.VISIBLE);
                } else {
                    mLocalViewNet.setVisibility(View.GONE);
                }
            }
            if (mRemoteUid == Long.parseLong(uid)) {
                if (txQuality == ThunderRtcConstant.NetworkQuality.THUNDER_QUALITY_VBAD) {
                    mRemoteViewNet.setVisibility(View.VISIBLE);
                } else {
                    mRemoteViewNet.setVisibility(View.GONE);
                }
            }
        }
    };

    @Override
    public void onUpdateChannelState(ChannelStateService.ChannelState fromState, ChannelStateService.ChannelState toState) {
        Log.d(TAG, "onUpdateChannelState-----fromState=" + fromState + "   --toState=" + toState);
    }

    private void reJoinChatroom() {
        HMR.getService(ChatRoomService.class).join(new ChatRoom(Long.parseLong(roomId)), new HashMap<>(16), new Challenges.JoiningCompletion() {
            @Override
            public void onReceiveChallenge(Challenges.Password challenge) {
            }

            @Override
            public void onReceiveChallenge(Challenges.AppChallenge challenge) {
            }

            @Override
            public void onSucceed() {

            }

            @Override
            public void onFailure(@NonNull Error err) {

            }
        });
    }
}
