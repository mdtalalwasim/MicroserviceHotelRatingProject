package com.mdtalalwasim.user.service.external.service;

import com.mdtalalwasim.user.service.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelServiceExternal {

    @GetMapping("/api/v1/hotels/{hotelId}")
    Hotel getHotel(@PathVariable String hotelId);
}
