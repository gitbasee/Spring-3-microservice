package com.hakeem.RatingService.service;

import com.hakeem.RatingService.entity.Ratings;

import java.util.List;

public interface RatingService {
    Ratings createHotelRating(Ratings ratings);

    List<Ratings> getAllHotelRating();

    List<Ratings> getAllRatingByUser(String userId);

    List<Ratings> getAllRatingForHotel(String hotelId);
}
