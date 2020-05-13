2
https://raw.githubusercontent.com/Aivacom/JLYLiveChat-android/master/app/src/main/java/com/mediaroom/ui/MemberListActivity.java
package com.mediaroom.ui;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hummer.im.Error;
import com.hummer.im.HMR;
import com.hummer.im._internals.log.Log;
import com.hummer.im._internals.log.trace.Trace;
import com.hummer.im.chatroom.ChatRoomService;
import com.hummer.im.model.id.ChatRoom;
import com.hummer.im.model.id.User;
import com.mediaroom.R;
import com.mediaroom.adapter.RoomMemberAdapter;
import com.mediaroom.bean.UserInfo;
import com.mediaroom.facade.HummerManager;
import com.mediaroom.utils.BaseActivity;
import com.mediaroom.utils.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MemberListActivity extends BaseActivity {
    private TextView mToolbarTitle;
    private ChatRoom mChatRoom;
    private String roomId;
    private RoomMemberAdapter adapter;
    private RecyclerView recyclerView;
    private List<UserInfo> userInfos = new ArrayList<>();
    private Long ownerId, mRemoteUid;
    private boolean isLive, isSubscribeRemote;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_member_list;
    }

    @Override
    protected void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        mToolbarTitle = findViewById(R.id.toolbar_title);
        toolbar.setTitle("");
        mToolbarTitle.setText("成员列表");
        toolbar.setNavigationOnClickListener(v -> finish());
        recyclerView = findViewById(R.id.recyclerView_members);

        adapter = new RoomMemberAdapter(new ArrayList<>(), MemberListActivity.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter.setOnItemChildClickListener(new RoomMemberAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(View view, int position) {
                android.util.Log.d(TAG, "onItemChildClick: " + view.getId());
                switch (view.getId()) {
                    case R.id.btn_muted:
                        mutedMember(adapter.getData().get(position).getUid(), position);
                        break;
                    case R.id.btn_kicked:
                        if (mRemoteUid == adapter.getData().get(position).getUid() && isSubscribeRemote) {
                            Toast.makeText(MemberListActivity.this, "请先取消与该用户订阅状态", Toast.LENGTH_SHORT).show();
                        } else {
                            kickMember(adapter.getData().get(position).getUid(), position);
                        }
                        break;
                    case R.id.btn_closeroom:
                        if (isLive) {
                            Toast.makeText(MemberListActivity.this, "请关闭直播再销毁房间", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (isSubscribeRemote) {
                            Toast.makeText(MemberListActivity.this, "请取消订阅再退出房间", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        HMR.getService(ChatRoomService.class).dismissChatRoom(mChatRoom, new HMR.Completion() {
                            @Override
                            public void onSuccess() {
                                finish();
                            }

                            @Override
                            public void onFailed(Error err) {
                                Toast.makeText(MemberListActivity.this, "销毁房间失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        roomId = intent.getStringExtra(Constant.ROOMID_KEY);
        ownerId = intent.getLongExtra(Constant.ROOMOWNERID_KEY, 0);
        mRemoteUid = intent.getLongExtra(Constant.ROOMISSUBCRIBE_MREMOTEUID_KEY, 0);
        isLive = intent.getBooleanExtra(Constant.ROOMISLIVE_KEY, false);
        isSubscribeRemote = intent.getBooleanExtra(Constant.ROOMISSUBCRIBE_KEY, false);
        mChatRoom = new ChatRoom(Long.parseLong(roomId));
        android.util.Log.d(TAG, "initData: " + ownerId);
        HummerManager.getInstance().obtainMemberList(MemberListActivity.this, mChatRoom, userInfos, ownerId, adapter);
    }


    private void kickMember(long uid, int position) {
        HMR.getService(ChatRoomService.class).kick(mChatRoom, new User(uid), null, new HMR.Completion() {
            @Override
            public void onSuccess() {
                Log.i("kickMember", String.format(Locale.US, "success, roomId:%d", mChatRoom.getId()));
                if (adapter.getData().get(position).getUid() == uid) {
                    adapter.getData().remove(position);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailed(Error err) {
                Toast.makeText(MemberListActivity.this, "踢出房间失败", Toast.LENGTH_SHORT).show();
                Log.i("kickMember", String.format(Locale.US, "failed, err:%s", err.toString()));
            }
        });
    }


    private void mutedMember(long uid, int position) {
        if (adapter.getData().get(position).isMuted()) {
            HMR.getService(ChatRoomService.class).unmuteMember(mChatRoom, new User(uid), null,
                    new HMR.Completion() {
                        @Override
                        public void onSuccess() {
                            if (adapter.getData().get(position).getUid() == uid) {
                                adapter.getData().get(position).setMuted(false);
                                adapter.notifyItemChanged(position, adapter.getData().get(position));//diff刷新脏数据
                            }
                        }

                        @Override
                        public void onFailed(Error err) {
                            String str = adapter.getData().get(position).isMuted() ? "恢复发言失败" : "禁言失败";
                            Toast.makeText(MemberListActivity.this, str, Toast.LENGTH_SHORT).show();
                            Log.i(TAG, Trace.once().method("unmuteMember").msg("failed, err:%s", err.toString()));
                        }
                    });
        } else {
            HMR.getService(ChatRoomService.class).muteMember(mChatRoom, new User(uid), null, new HMR.Completion() {
                @Override
                public void onSuccess() {
                    if (adapter.getData().get(position).getUid() == uid) {
                        adapter.getData().get(position).setMuted(true);
                        adapter.notifyItemChanged(position, adapter.getData().get(position));//diff刷新脏数据
                    }
                }

                @Override
                public void onFailed(Error err) {
                    Log.i(TAG, Trace.once().method("muteMember").msg("failed, err:%s", err.toString()));
                }
            });
        }
    }
}
