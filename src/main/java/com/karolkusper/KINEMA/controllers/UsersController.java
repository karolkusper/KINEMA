package com.karolkusper.KINEMA.controllers;

import com.karolkusper.KINEMA.entity.User;
import com.karolkusper.KINEMA.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    private UserServiceImpl userService;

    @Autowired
    public UsersController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Integer userId){
        return userService.findById(userId);
    }


    @PostMapping()
    public User addUser(@RequestBody User user)
    {
        user.setId(0);

        return userService.save(user);
    }

    @PutMapping()
    public User updateUser(@RequestBody User updatedUser){
        User existingUser=userService.findById(updatedUser.getId());
        if(existingUser !=null)
        {
            return userService.save(updatedUser);
        }
        else
        {
            throw new RuntimeException("There is no user with id="+updatedUser.getId());
        }
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Integer userId){
        userService.deleteById(userId);
    }

}
