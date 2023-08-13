package com.hakeem.user.service.UserService;

import com.hakeem.user.service.UserService.entity.Ratings;
import com.hakeem.user.service.UserService.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceApplicationTest {
    @Autowired
    private RatingService ratingService;

    @Test
    void contextLoad() {

    }

    @Test
    void createRatingFromUserServiceTest() {
        Ratings ratingObj = Ratings.builder().rating(9).hotelId("").userId("").feedback("this rating is created from test for checking POST using feign client").build();
        Ratings rating = ratingService.createRating(ratingObj);
        System.out.println("rating = " + rating);
    }
}