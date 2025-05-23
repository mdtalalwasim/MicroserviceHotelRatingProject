package com.mdtalalwasim.hotel.services;

import com.mdtalalwasim.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {
    Hotel createHotel(Hotel hotel);
    Hotel getHotel(String hotelId);
    void deleteHotel(String hotelId);
    Hotel updateHotel(Hotel hotel, String hotelId);
    List<Hotel> getAllHotels();
}
