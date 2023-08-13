package com.hakeem.RatingService.controller;

import com.hakeem.RatingService.entity.Ratings;
import com.hakeem.RatingService.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @PostMapping
    @PreAuthorize("hasAuthority('Admin')")
    ResponseEntity<Ratings> createRatingForHotel(@RequestBody Ratings ratings) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.createHotelRating(ratings));
    }

    @GetMapping
    ResponseEntity<List<Ratings>> getAllRatings() {
        return ResponseEntity.ok().body(ratingService.getAllHotelRating());
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    ResponseEntity<List<Ratings>> getAllRatingByUser(@PathVariable String userId) {
        return ResponseEntity.ok().body(ratingService.getAllRatingByUser(userId));
    }

    @GetMapping("/hotel/{hotelId}")
    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    ResponseEntity<List<Ratings>> getAllRatingByHotel(@PathVariable String hotelId) {
        return ResponseEntity.ok().body(ratingService.getAllRatingForHotel(hotelId));
    }
}
