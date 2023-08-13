package com.hakeem.RatingService.service.impl;

import com.hakeem.RatingService.entity.Ratings;
import com.hakeem.RatingService.repository.RatingRepository;
import com.hakeem.RatingService.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Ratings createHotelRating(Ratings ratings) {
        ratings.setRatingId(UUID.randomUUID().toString());
        return ratingRepository.save(ratings);
    }

    @Override
    public List<Ratings> getAllHotelRating() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Ratings> getAllRatingByUser(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Ratings> getAllRatingForHotel(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
