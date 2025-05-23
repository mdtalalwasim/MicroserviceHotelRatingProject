package com.mdtalalwasim.rating.repositories;

import com.mdtalalwasim.rating.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {

    List<Rating> findByHotelId(String hotelId);

    List<Rating> findByUserId(String userId);
}
