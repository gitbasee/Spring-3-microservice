package com.hakeem.HotelService.service.Impl;

import com.hakeem.HotelService.entity.Hotel;
import com.hakeem.HotelService.exceptions.ResourceNotFoundException;
import com.hakeem.HotelService.repository.HotelRepository;
import com.hakeem.HotelService.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel createHotel(Hotel hotel) {
        hotel.setId(UUID.randomUUID().toString());
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotel() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(String id) {
        return hotelRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Hotel not found for given ID " + id));
    }
}
