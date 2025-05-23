package com.mdtalalwasim.hotel.controllers;

import com.mdtalalwasim.hotel.entities.Hotel;
import com.mdtalalwasim.hotel.services.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(hotel));
    }
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId){
        return ResponseEntity.ok(hotelService.getHotel(hotelId));
    }
    @DeleteMapping("/{hotelId}")
    public ResponseEntity<String> deleteHotel(@PathVariable String hotelId){
        hotelService.deleteHotel(hotelId);
        return ResponseEntity.ok("Hotel deleted successfully");
    }
    @PutMapping("/{hotelId}")
    public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel, @PathVariable String hotelId){
        return ResponseEntity.ok(hotelService.updateHotel(hotel, hotelId));
    }
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels(){
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

}
