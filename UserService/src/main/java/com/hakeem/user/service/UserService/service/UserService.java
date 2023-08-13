package com.hakeem.user.service.UserService.service;

import com.hakeem.user.service.UserService.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    List<User> getAllUser();

    User getUser(String userId);

}
