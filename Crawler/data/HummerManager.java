2
https://raw.githubusercontent.com/Aivacom/JLYLiveChat-android/master/app/src/main/java/com/mediaroom/facade/HummerManager.java
package com.mediaroom.facade;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.hummer.im.Error;
import com.hummer.im.HMR;
import com.hummer.im._internals.log.Log;
import com.hummer.im.chatroom.ChatRoomService;
import com.hummer.im.model.chat.Message;
import com.hummer.im.model.id.ChatRoom;
import com.hummer.im.model.id.User;
import com.hummer.im.service.ChatService;
import com.mediaroom.adapter.ChatMessageAdapter;
import com.mediaroom.bean.ChatMessage;
import com.mediaroom.bean.UserInfo;
import com.mediaroom.ui.ChatRoomActivity;
import com.mediaroom.utils.BaseActivity;
import com.mediaroom.utils.BaseAdapter;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import static com.hummer.im._internals.chatsvc.ChatServiceImpl.TAG;

public class HummerManager {

    private static HummerManager instance;
    //此常量为维护Hummer,上层接入可以为vm子属性进行自定义
    public static boolean closeChatRoom = false;
    public static boolean isOwner = false;
    public static boolean isForceOut = false;
    public static boolean isKick = false;
    public static boolean isOwnerOnLine = false;

    public static HummerManager getInstance() {
        if (instance == null) {
            instance = new HummerManager();
        }
        return instance;
    }

    public void obtainMemberList(Context context, @NonNull ChatRoom mChatRoom, List<UserInfo> userInfos, long ownerId, BaseAdapter adapter) {
        ((BaseActivity) context).showDialogProgress();
        UserInfo userInfo = new UserInfo();//房主放在0位置
        userInfo.setRole("owner");
        userInfo.setUid(ownerId);
        userInfos.add(userInfo);
        fetchMembers(context, mChatRoom, userInfos, adapter);
    }


    private void fetchMembers(Context context, @NonNull ChatRoom mChatRoom, List<UserInfo> userInfos, BaseAdapter adapter) {
        HMR.getService(ChatRoomService.class).fetchMembers(mChatRoom, 100, 0, new HMR.CompletionArg<List<User>>() {
            @Override
            public void onSuccess(List<User> users) {
                android.util.Log.d(TAG, ">>>>uid: " + userInfos.get(0).getUid());
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).getId() != userInfos.get(0).getUid()) {//去除owner
                        UserInfo userInfo = new UserInfo();
                        userInfo.setUid(users.get(i).getId());
                        userInfos.add(userInfo);
                    }
                }
                if (!isOwner) {
                    ((BaseActivity) context).dissMissDialogProgress();
                    adapter.setData(userInfos);
                } else {
                    fetchMutedUsers(context, mChatRoom, userInfos, adapter);
                }
                Log.i("fetchMember", String.format(Locale.US, "success, member size:%d", users.size()));
            }

            @Override
            public void onFailed(Error err) {
                ((BaseActivity) context).dissMissDialogProgress();
                Log.i("fetchMember", String.format(Locale.US, "failed, err info:%s", err.toString()));
            }
        });
    }

    private void fetchMutedUsers(Context context, @NonNull ChatRoom mChatRoom, List<UserInfo> userInfos, BaseAdapter adapter) {
        HMR.getService(ChatRoomService.class).fetchMutedUsers(mChatRoom, new HMR.CompletionArg<Set<User>>() {
            @Override
            public void onSuccess(Set<User> members) {

                if (members.size() > 0) {
                    for (int i = 0; i < userInfos.size(); i++) {
                        for (User user : members) {
                            if (user.getId() == userInfos.get(i).getUid()) {
                                userInfos.get(i).setMuted(true);
                                android.util.Log.d(TAG, "fetchMutedUsers onSuccess: " + JSON.toJSONString(userInfos));
                            }
                        }
                    }
                }
                android.util.Log.d(TAG, "onSuccess: " + JSON.toJSONString(userInfos));
                adapter.resetData(userInfos);
                ((BaseActivity) context).dissMissDialogProgress();
            }

            @Override
            public void onFailed(Error error) {
                ((BaseActivity) context).dissMissDialogProgress();
                android.util.Log.e(TAG, "onFailed: " + error.toString());
            }
        });
    }

    public void isOwnerOnLine(@NonNull ChatRoom mChatRoom, String uid) {
        HMR.getService(ChatRoomService.class)
                .fetchRoleMembers(mChatRoom, true, new HMR.CompletionArg<Map<String, List<User>>>() {
                    @Override
                    public void onSuccess(Map<String, List<User>> members) {
                        if (members != null && members.get("owner") != null && members.get("owner").size() >= 1) {
                            for (int i = 0; i < members.size(); i++) {
                                if (members.get("owner").get(0).getId() == Long.parseLong(uid)) {
                                    isOwnerOnLine = true;
                                } else {
                                    isOwnerOnLine = false;
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailed(Error err) {
                        android.util.Log.d(TAG, "onFailed: " + err.toString());
                    }
                });
    }

    public void sendSignalMessage(Context context, ChatRoom mChatRoom, String content, User target) {
        HMR.getService(ChatService.class).send(new Message(mChatRoom, ChatRoomService.Signal.unicast(target, content)), new HMR.Completion() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onFailed(Error err) {
                ((BaseActivity) context).dissMissDialogProgress();
            }
        });
    }


    public void onMemberKicked(Context context, ChatRoom chatRoom, User admin, List<User> member, ChatMessageAdapter adapter, RecyclerView recyclerView) {
        for (int i = 0; i < member.size(); i++) {
            if (HMR.getMe().getId() == member.get(i).getId() && admin.getId() == HMR.getMe().getId()) {
                HummerManager.isForceOut = true;
                ((ChatRoomActivity) context).finish();
                break;
            }
            if (HMR.getMe().getId() == member.get(i).getId()) {
                HMR.getService(ChatRoomService.class)
                        .fetchRoleMembers(chatRoom, false, new HMR.CompletionArg<Map<String, List<User>>>() {
                            @Override
                            public void onSuccess(Map<String, List<User>> members) {
                                if (HMR.getMe().getId() == members.get("owner").get(0).getId()) {
                                    HummerManager.isOwner = true;
                                } else {
                                    HummerManager.isOwner = false;
                                }
                                HummerManager.isKick = true;
                                ((ChatRoomActivity) context).finish();
                            }

                            @Override
                            public void onFailed(Error err) {
                                ((ChatRoomActivity) context).finish();
                                android.util.Log.d(TAG, "onFailed: " + err.toString());
                            }
                        });

            } else {
                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setMsgType(adapter.TYPE_ITEM_CHATMESSAGE_TIP);
                chatMessage.setMessage(member.get(i).getId() + "被踢出房间");
                adapter.addNewData(chatMessage);
                recyclerView.scrollToPosition(adapter.getItemCount() - 1);//此句为设置显示
            }
        }

    }

}
