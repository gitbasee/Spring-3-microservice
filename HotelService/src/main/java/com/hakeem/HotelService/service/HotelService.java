package com.hakeem.HotelService.service;

import com.hakeem.HotelService.entity.Hotel;

import java.util.List;

public interface HotelService {
    Hotel createHotel(Hotel hotel);

    List<Hotel> getAllHotel();

    Hotel getHotel(String id);
}
