package com.kamals.algo.dao.impl;

import com.kamals.algo.dao.UserDao;
import com.kamals.algo.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class H2UserDaoImpl implements UserDao
{
    private static List<User> DB = new ArrayList<>();

    @Override
    public int addUser(UUID uuid, User user)
    {
        DB.add(new User(uuid, user.getName(), user.getMobileNumber(), user.getEmailId()));
        return 1;
    }

    @Override
    public List<User> getAllUsers()
    {
        return DB;
    }

    @Override
    public Optional<User> getUserById(UUID uuid)
    {
        return DB.stream().filter(user -> user.getUuid().equals(uuid)).findFirst();
    }

    @Override
    public int updateUserById(UUID uuid, User user)
    {
        return getUserById(uuid)
                .map(u -> {
                    int index = DB.indexOf(u);
                    if (index >= 0)
                    {
                        DB.set(index, new User(uuid, user.getName(), user.getMobileNumber(), user.getEmailId()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }

    @Override
    public int deleteUserById(UUID uuid)
    {
        Optional<User> userOptional = getUserById(uuid);
        if (userOptional.isPresent())
        {
            DB.remove(userOptional.get());
            return 1;
        }
        return 0;
    }
}
