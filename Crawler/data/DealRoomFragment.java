2
https://raw.githubusercontent.com/Aivacom/JLYLiveChat-android/master/app/src/main/java/com/mediaroom/ui/DealRoomFragment.java
package com.mediaroom.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hummer.im.Error;
import com.hummer.im.HMR;
import com.hummer.im._internals.mq.Source;
import com.hummer.im.chatroom.Challenges;
import com.hummer.im.chatroom.ChatRoomInfo;
import com.hummer.im.chatroom.ChatRoomService;
import com.hummer.im.model.id.ChatRoom;
import com.hummer.im.service.MQService;
import com.mediaroom.BuildConfig;
import com.mediaroom.R;
import com.mediaroom.facade.FacadeRtcManager;
import com.mediaroom.utils.BaseFragment;
import com.mediaroom.utils.Constant;
import com.mediaroom.utils.EditTextUtil;
import com.mediaroom.utils.Utils;
import com.thunder.livesdk.ThunderRtcConstant;

import java.util.HashMap;

public class DealRoomFragment extends BaseFragment implements View.OnClickListener {
    private TextView mToolbarTitle, mToolbarRight, tvAppVersion;
    private EditText etLoginRoomid;
    private Button etBtnJoinRoom, etBtnCreateRoom;
    private String localLiveRoomId;
    private String roomId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View mRootView = inflater.inflate(R.layout.fragment_deal_room, null);
        Toolbar toolbar = mRootView.findViewById(R.id.toolbar);
        mToolbarTitle = mRootView.findViewById(R.id.toolbar_title);
        mToolbarRight = mRootView.findViewById(R.id.toolbar_right);
        toolbar.setTitle("");
        mToolbarTitle.setText("UID:" + Constant.uid);
        mToolbarRight.setOnClickListener(this::onClick);
        etLoginRoomid = mRootView.findViewById(R.id.et_roomid);
        etBtnJoinRoom = mRootView.findViewById(R.id.btn_join_room);
        etBtnCreateRoom = mRootView.findViewById(R.id.btn_create_room);
        tvAppVersion = mRootView.findViewById(R.id.tv_app_version);

        etLoginRoomid.setOnClickListener(this::onClick);
        etBtnJoinRoom.setOnClickListener(this::onClick);
        etBtnCreateRoom.setOnClickListener(this::onClick);
        tvAppVersion.setText(getString(R.string.app_version, BuildConfig.VERSION_NAME));
        EditTextUtil.checkRoomIdEditTextChanged(etLoginRoomid, etBtnJoinRoom);
        FacadeRtcManager.getInstance().setJoinThunderRoomObserver(observer);
        return mRootView;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && mToolbarTitle!=null) {
            mToolbarTitle.setText("UID:" + Constant.uid);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_right:
                logout();
                break;
            case R.id.btn_join_room:
                showDialogProgress();
                roomId = etLoginRoomid.getText().toString();
                joinChatroom();
                break;
            case R.id.btn_create_room:
                createRoom();
                break;
        }
    }

    private void createRoom() {
        showDialogProgress();
        HMR.getService(ChatRoomService.class).createChatRoom(new ChatRoomInfo("随机测试", "随机测试", null, null), new HMR.CompletionArg<ChatRoom>() {
            @Override
            public void onSuccess(ChatRoom result) {
                roomId = Long.toString(result.getId());
                joinChatroom();
            }

            @Override
            public void onFailed(Error error) {
                dissMissDialogProgress();
                if (error.code == Error.Code.NetworkNotFound) {
                    Toast.makeText(getActivity(), "网络无法连接", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "创建房间失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    FacadeRtcManager.JoinThunderRoomObserver observer = new FacadeRtcManager.JoinThunderRoomObserver() {
        @Override
        public void onJoinRoomSuccess(String room, String uid, int elapsed) {
            super.onJoinRoomSuccess(room, uid, elapsed);
            //显示Thunderbolt相关界面
            dissMissDialogProgress();
            Intent intent = new Intent(getActivity(), ChatRoomActivity.class);
            intent.putExtra(Constant.ROOMID_KEY, roomId);
            startActivity(intent);
        }
    };

    private void logout() {
        showDialogProgress();
        HMR.getService(MQService.class)
                .removeSource(new Source(new Source.Shared(Constant.CHAT_GROUPID, null)));
        HMR.close(new HMR.Completion() {
            @Override
            public void onSuccess() {
                dissMissDialogProgress();
                Utils.setIsLogin(getActivity(),false);
                ((MainActivity) getActivity()).showFragment(MainActivity.SHOW_LOGINFRAGMENT);
            }

            @Override
            public void onFailed(Error err) {
                dissMissDialogProgress();
                Toast.makeText(getActivity(), "退出登录失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void joinChatroom() {
        HMR.getService(ChatRoomService.class).join(new ChatRoom(Long.parseLong(roomId)), new HashMap<>(16), new Challenges.JoiningCompletion() {
            @Override
            public void onReceiveChallenge(Challenges.Password challenge) {
            }

            @Override
            public void onReceiveChallenge(Challenges.AppChallenge challenge) {
            }

            @Override
            public void onSucceed() {
                localLiveRoomId = roomId + HMR.getMe().getId();
//                ThunderEngine.setLogLevel(ThunderRtcConstant.ThunderLogLevel.THUNDERLOG_LEVEL_ERROR);
                FacadeRtcManager.getInstance().setRoomConfig(ThunderRtcConstant.ThunderRtcProfile.THUNDER_PROFILE_DEFAULT, ThunderRtcConstant.RoomConfig.THUNDER_ROOMCONFIG_LIVE);
                FacadeRtcManager.getInstance().joinRoom(Constant.token, localLiveRoomId, String.valueOf(HMR.getMe().getId()));
            }

            @Override
            public void onFailure(@NonNull Error err) {
                dissMissDialogProgress();
                if (err.code == Error.Code.ResourceNotFound) {
                    Toast.makeText(getActivity(), "房间不存在", Toast.LENGTH_SHORT).show();
                } else if (err.code == Error.Code.NetworkNotFound) {
                    Toast.makeText(getActivity(), "网络无法连接", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "加入房间失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static DealRoomFragment newInstance() {
        return new DealRoomFragment();
    }
}
