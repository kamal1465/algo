package com.kamals.algo.service;

import com.kamals.algo.dao.UserDao;
import com.kamals.algo.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService
{
    @Resource(name = "userDaoImpl")
    private UserDao userDao;

    public int addUser(User user)
    {
        return userDao.addUser(user);
    }

    public List<User> getAllUsers()
    {
        return userDao.getAllUsers();
    }

    public Optional<User> getUserById(UUID uuid)
    {
        return userDao.getUserById(uuid);
    }

    public int deleteUserById(UUID uuid)
    {
        return userDao.deleteUserById(uuid);
    }

    public int updateUserById(UUID uuid, User user)
    {
        return userDao.updateUserById(uuid, user);
    }
}
