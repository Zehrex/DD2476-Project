34
https://raw.githubusercontent.com/1127140426/tensquare/master/tensquare_friend/src/main/java/com/tensquare/friend/client/UserClient.java
package com.tensquare.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 李聪
 * @date 2020/2/19 13:30
 */
@FeignClient("tensquare-user")
public interface UserClient {

    @RequestMapping(value = "/{userid}/{friendid}/{x}",method = RequestMethod.PUT)
    public void updatefanscountandfollowcount(@PathVariable("userid") String userid, @PathVariable("friendid") String friendid, @PathVariable("x") int x);
}
