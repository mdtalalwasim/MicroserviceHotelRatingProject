package com.mdtalalwasim.rating.services;

import com.mdtalalwasim.rating.entities.Rating;

import java.util.List;

public interface RatingService {

    //create rating
    Rating createRating(Rating rating);

    //get rating by id
    Rating getRating(String ratingId);

    //delete rating by ratingId
    void deleteRating(String ratingId);

    //update rating
    Rating updateRating(Rating rating, String ratingId);

    // get all ratings
    List<Rating> getAllRatings();

    //get all ratings by user id
    List<Rating> getAllByUserId(String userId);

    //get all ratings by hotel id
    List<Rating>  getAllByHotelId(String hotelId);
}
