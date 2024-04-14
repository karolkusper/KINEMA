package com.karolkusper.KINEMA.service.User;

import com.karolkusper.KINEMA.entity.User;

import java.util.List;

public interface UserService{
    List<User> findAll();
    User findById(int id);

    User save(User employee);
    void deleteById(int id);
}
