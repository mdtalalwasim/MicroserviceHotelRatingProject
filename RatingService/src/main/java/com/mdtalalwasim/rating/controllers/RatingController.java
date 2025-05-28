package com.mdtalalwasim.rating.controllers;

import com.mdtalalwasim.rating.entities.Rating;
import com.mdtalalwasim.rating.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ratings")
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;


    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.createRating(rating));
    }

    //get single rating
    @GetMapping("/{ratingId}")
    public ResponseEntity<Rating> getRating(@PathVariable String ratingId){
        return ResponseEntity.ok(ratingService.getRating(ratingId));
    }

    @DeleteMapping("/{ratingId}")
    public void deleteRating(@PathVariable String ratingId){
        ratingService.deleteRating(ratingId);
    }

    @PutMapping("/{ratingId}")
    public Rating updateRating(@RequestBody Rating rating, @PathVariable String ratingId){
        return ratingService.updateRating(rating, ratingId);
    }
    @GetMapping
    public List<Rating> getAllRatings(){
        return ratingService.getAllRatings();
    }

    @GetMapping("/users/{userId}")
    public List<Rating> getAllByUserId(@PathVariable String userId){
        return ratingService.getAllByUserId(userId);
    }

    @GetMapping("/hotels/{hotelId}")
    public List<Rating> getAllByHotelId(@PathVariable String hotelId){
        return ratingService.getAllByHotelId(hotelId);
    }

}
