package com.kamals.algo.dao;

import com.kamals.algo.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao
{
    int addUser(UUID uuid, User user);

    default int addUser(User user)
    {
        UUID uuid = UUID.randomUUID();
        return addUser(uuid, user);
    }

    List<User> getAllUsers();

    Optional<User> getUserById(UUID uuid);

    int updateUserById(UUID uuid, User user);

    int deleteUserById(UUID uuid);
}
