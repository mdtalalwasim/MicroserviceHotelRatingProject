package com.mdtalalwasim.user.service.services.impl;

import com.mdtalalwasim.user.service.entities.User;
import com.mdtalalwasim.user.service.exception.ResourceNotFoundException;
import com.mdtalalwasim.user.service.repositories.UserRepository;
import com.mdtalalwasim.user.service.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with given ID :"+userId));
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with given ID :" + userId));
        userRepository.delete(user);
    }

    @Override
    public User updateUser(User user, String userId) {
        userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with given ID :" + userId));
        user.setUserId(userId);
        return userRepository.save(user);
    }
}
