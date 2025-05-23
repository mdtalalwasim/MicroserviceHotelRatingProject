package com.mdtalalwasim.hotel.services.impl;

import com.mdtalalwasim.hotel.entities.Hotel;
import com.mdtalalwasim.hotel.exception.ResourceNotFoundException;
import com.mdtalalwasim.hotel.repositories.HotelRepository;
import com.mdtalalwasim.hotel.services.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public Hotel createHotel(Hotel hotel) {
        String randomHotelsId = UUID.randomUUID().toString();
        hotel.setId(randomHotelsId);
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel getHotel(String hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel not found with given ID :" + hotelId));
    }

    @Override
    public void deleteHotel(String hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel not found with given ID :" + hotelId));
        hotelRepository.delete(hotel);
    }

    @Override
    public Hotel updateHotel(Hotel hotel, String hotelId) {
        hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel not found with given ID :" + hotelId));
        hotel.setId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
}
