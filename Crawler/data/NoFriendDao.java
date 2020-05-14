34
https://raw.githubusercontent.com/1127140426/tensquare/master/tensquare_friend/src/main/java/com/tensquare/friend/dao/NoFriendDao.java
package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 李聪
 * @date 2020/2/19 12:52
 */
public interface NoFriendDao extends JpaRepository<NoFriend,String> {
    public NoFriend findByUseridAndFriendid(String userid, String friendid);
}
