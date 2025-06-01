package com.mdtalalwasim.user.service.controllers;

import com.mdtalalwasim.user.service.entities.User;
import com.mdtalalwasim.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    //create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User createdUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    //get singleUser
    @GetMapping("/{userId}")
    @CircuitBreaker(name = "userRatingHotelServiceBreaker", fallbackMethod = "userRatingHotelServiceFallBack")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    //userRatingHotelServiceFallBack fallback method
    public ResponseEntity<User> userRatingHotelServiceFallBack(@PathVariable String userId, Exception ex){
        log.info("Fallback method called because Service is Down: "+ex.getMessage());
        User user = User.builder()
                .email("myemail@gmail.com")
                .name("name")
                .userId("12345654321")
                .build();
        return ResponseEntity.ok(user);
    }

    //fetch All user
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        userService.getAllUser();
        return ResponseEntity.ok(userService.getAllUser());
    }

    //delete userd by id
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }

    //update user
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String userId){
        User updateUser = userService.updateUser(user, userId);
        return ResponseEntity.ok(updateUser);
    }
}
