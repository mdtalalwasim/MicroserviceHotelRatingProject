package com.mdtalalwasim.user.service.services;

import com.mdtalalwasim.user.service.entities.User;

import java.util.List;

public interface UserService {

    //create
    User saveUser(User user);
    List<User> getAllUser();
    User getUser(String userId);
    void deleteUser(String userId);
    User updateUser(User user, String userId);



}
