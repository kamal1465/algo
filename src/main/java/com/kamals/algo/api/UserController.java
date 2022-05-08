package com.kamals.algo.api;

import com.kamals.algo.model.User;
import com.kamals.algo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/user")
@RestController
public class UserController
{
    private final UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping
    public void addUser(@Valid @NotNull @RequestBody User user)
    {
        userService.addUser(user);
    }

    @GetMapping
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/{id}")
    public User getUser(@PathVariable("id") UUID uuid)
    {
        return userService.getUserById(uuid).orElse(null);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable("id") UUID uuid)
    {
        userService.deleteUserById(uuid);
    }

    @PutMapping(path = "/{id}")
    public void updateUser(@PathVariable("id") UUID uuid,
                           @Valid @NotNull @RequestBody User user)
    {
        userService.updateUserById(uuid, user);
    }
}
