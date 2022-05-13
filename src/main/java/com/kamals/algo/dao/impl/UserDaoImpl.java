package com.kamals.algo.dao.impl;

import com.kamals.algo.dao.UserDao;
import com.kamals.algo.dao.UserRepository;
import com.kamals.algo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserDaoImpl implements UserDao
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public int addUser(UUID uuid, User user)
    {
        userRepository.save(new User(uuid, user.getName(), user.getMobileNumber(), user.getEmailId()));
        return 1;
    }

    @Override
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(UUID uuid)
    {
        return userRepository.findById(uuid);
    }

    @Override
    public int updateUserById(UUID uuid, User user)
    {
        user.setUuid(uuid);
        userRepository.save(user);
        return 1;
    }

    @Override
    public int deleteUserById(UUID uuid)
    {
        userRepository.deleteById(uuid);
        return 1;
    }
}
