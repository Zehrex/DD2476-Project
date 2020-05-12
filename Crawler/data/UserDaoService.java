2
https://raw.githubusercontent.com/joneconsulting/springbootproject/master/src/main/java/com/example/demo/dao/UserDaoService.java
package com.example.demo.dao;

import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
// implements (class -> interface)
// extends (class -> class)
// interface -> class (X)
// extends (interface -> interface)
@Component
public class UserDaoService implements IUserService {
    private static List<User> list = new ArrayList<>();

    static {
        list.add(new User(1, "Kenneth", new Date(), "pwd1",
                "701010-1111111"));
        list.add(new User(2, "Elena", new Date(), "pwd2",
                "801010-2222222"));
        list.add(new User(3, "Alice", new Date(), "pwd3",
                "901010-1111111"));
    }

    @Override
    public List<User> getUserList() {
        return list;
    }

    @Override
    public User getUser(Integer id) {
         for (User user : list) {
             if (id.equals(user.getId())) {
                 return user;
             }
         }

         // exception 처리 -> UserNotFoundException 반환
         return null;
    }

    @Override
    public User createUser(User newUser) {
        if (newUser.getId() == null) {
            newUser.setId(list.get(list.size() - 1).getId() + 1);
        }
        list.add(newUser);
        return newUser;
    }

    @Override
    public User modifyUser(User modifyUser) {
        Iterator<User> itUsers = list.iterator();
        while (itUsers.hasNext()) {
            User user = itUsers.next();
            if (user.getId() == modifyUser.getId()) {
                user.setName(modifyUser.getName());
                user.setJoinDate(modifyUser.getJoinDate());
                user.setPassword(modifyUser.getPassword());
                user.setSsn(modifyUser.getSsn());

                return user;
            }
        }
        return null;
    }

    @Override
    public User removeUser(Integer id) {
        Iterator<User> itUsers = list.iterator();
        // list -> ordering
        // set -> set ordering, not duplicate
        // map(key, value), not ordering, duplicate
            // HashMap, HashTable
            // SortedMap
        while (itUsers.hasNext()) {
            User user = itUsers.next();
            if (user.getId() == id) {
                itUsers.remove();
                return user;
            }
        }

        return null;
    }
}
