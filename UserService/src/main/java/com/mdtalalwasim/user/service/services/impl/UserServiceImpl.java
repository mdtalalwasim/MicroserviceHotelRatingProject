package com.mdtalalwasim.user.service.services.impl;

import com.mdtalalwasim.user.service.entities.Hotel;
import com.mdtalalwasim.user.service.entities.Rating;
import com.mdtalalwasim.user.service.entities.User;
import com.mdtalalwasim.user.service.exception.ResourceNotFoundException;
import com.mdtalalwasim.user.service.repositories.UserRepository;
import com.mdtalalwasim.user.service.services.UserService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final EurekaClient eurekaClient;

    private final String API_PATH_RATINGS = "api/v1/ratings/users/";
    private final String API_PATH_HOTELS = "api/v1/hotels/";

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with given ID :" + userId));
        //TODO: Refractor the code
        //fetch rating of the above user from the rating server::
        String ratingServiceHomeUrl = eurekaClient.getNextServerFromEureka("RATING-SERVICE", false).getHomePageUrl();
        String hotelServiceHomeUrl = eurekaClient.getNextServerFromEureka("HOTEL-SERVICE", false).getHomePageUrl();

        String ratingUrl = ratingServiceHomeUrl + API_PATH_RATINGS +user.getUserId();

        String hotelUrl = hotelServiceHomeUrl + API_PATH_HOTELS;

        ResponseEntity<List<Rating>> ratingResponse = restTemplate.exchange(
                ratingUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Rating>>() {
                }
        );

        List<Rating> ratings = Optional.ofNullable(ratingResponse.getBody()).orElseThrow(() -> new ResourceNotFoundException("No Rating found for user with id :" + userId));


        List<Rating> ratingsList = ratings.stream().map(rating -> {
            ResponseEntity<Hotel> hotel = restTemplate
                    .exchange(
                            hotelUrl + rating.getHotelId(),
                            HttpMethod.GET,
                            null,
                            Hotel.class
                    );

            rating.setHotel(hotel.getBody());
            return rating;

        }).collect(Collectors.toList());

        user.setRatings(ratingsList);
        return user;
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with given ID :" + userId));
        userRepository.delete(user);
    }

    @Override
    public User updateUser(User user, String userId) {
        userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with given ID :" + userId));
        user.setUserId(userId);
        return userRepository.save(user);
    }
}
