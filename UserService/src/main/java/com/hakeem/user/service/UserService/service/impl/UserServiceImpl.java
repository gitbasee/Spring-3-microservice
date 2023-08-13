package com.hakeem.user.service.UserService.service.impl;

import com.hakeem.user.service.UserService.entity.Hotel;
import com.hakeem.user.service.UserService.entity.Ratings;
import com.hakeem.user.service.UserService.entity.User;
import com.hakeem.user.service.UserService.exceptions.ResourceNotFoundException;
import com.hakeem.user.service.UserService.external.services.HotelService;
import com.hakeem.user.service.UserService.repository.UserRepository;
import com.hakeem.user.service.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Override
    public User saveUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for given Id " + userId));
        //        Call this url to get hotel rating details for particular user id
        //        http://localhost:8083/rating/user/4f68f206-30d1-4fe9-b79d-9535a7d3e87e
        Ratings[] ratings = restTemplate.getForObject("http://RATING-SERVICE/rating/user/" + user.getUserId(), Ratings[].class);

        List<Ratings> userRatings = Arrays.stream(ratings).collect(Collectors.toList());

        List<Ratings> ratingList = userRatings.stream().map(eachRatings -> {
            //       Call getHotelByID http://localhost:8082/hotel/ec5d775b-956f-4593-87d1-e53f2889ff82
            //       ResponseEntity<Hotel> hotelResponse = restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/" + eachRatings.getHotelId(), Hotel.class);
            //       Hotel hotel = hotelResponse.getBody();
            Hotel hotel = hotelService.getHotel(eachRatings.getHotelId());
            eachRatings.setHotel(hotel);
            return eachRatings;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;
    }
}
