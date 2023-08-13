package com.hakeem.user.service.UserService.controller;

import com.hakeem.user.service.UserService.entity.User;
import com.hakeem.user.service.UserService.service.UserService;
import com.hakeem.user.service.UserService.service.impl.UserServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    int retryCount = 1;
    @Autowired
    private UserService userService;
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/{userID}")
    @CircuitBreaker(name = "Rating-Hotel-breaker", fallbackMethod = "ratingHotelFallBack")
    @Retry(name = "Rating-Hotel-Retry", fallbackMethod = "ratingHotelFallBack")
    @RateLimiter(name = "Rating-Hotel-RateLimiter", fallbackMethod = "ratingHotelFallBack")
    public ResponseEntity<User> getSingleUser(@PathVariable("userID") String userId) {
        System.out.println("retry Count = " + retryCount);
        retryCount++;
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    ResponseEntity ratingHotelFallBack(String userID, Exception ex) {
        System.out.println("fallback is executed as service is down for userID = " + userID + " exception is " + ex.getMessage());
        return ResponseEntity.ok("Failed");
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }
}
