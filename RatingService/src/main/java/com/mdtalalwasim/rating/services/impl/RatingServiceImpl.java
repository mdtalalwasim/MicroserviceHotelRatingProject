package com.mdtalalwasim.rating.services.impl;

import com.mdtalalwasim.rating.entities.Rating;
import com.mdtalalwasim.rating.repositories.RatingRepository;
import com.mdtalalwasim.rating.services.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    @Override
    public Rating createRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public Rating getRating(String ratingId) {
        return ratingRepository.findById(ratingId).orElseThrow(()->new RuntimeException("Rating not found with given id :"+ratingId));
    }

    @Override
    public void deleteRating(String ratingId) {
        ratingRepository.deleteById(ratingId);
    }

    @Override
    public Rating updateRating(Rating rating, String ratingId) {
        return null;
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getAllByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getAllByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
