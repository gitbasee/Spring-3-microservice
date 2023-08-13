package com.hakeem.user.service.UserService.external.services;

import com.hakeem.user.service.UserService.entity.Ratings;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
    @PostMapping("rating")
    Ratings createRating(@RequestBody Ratings createRatings);
}
