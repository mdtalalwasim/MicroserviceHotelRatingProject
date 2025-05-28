package com.mdtalalwasim.user.service.services.impl;

import com.mdtalalwasim.user.service.entities.Rating;
import com.mdtalalwasim.user.service.entities.User;
import com.mdtalalwasim.user.service.exception.ResourceNotFoundException;
import com.mdtalalwasim.user.service.repositories.UserRepository;
import com.mdtalalwasim.user.service.services.UserService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final EurekaClient eurekaClient;

    private final String API_PATH = "/api/v1/ratings/users/";
    private final String API_PATH_HOTEL = "/api/v1/hotels/";

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

        //fetch rating of the above user from rating server::
        InstanceInfo nextServerFromEureka = eurekaClient.getNextServerFromEureka("rating-service", false);
        String homePageUrl = nextServerFromEureka.getHomePageUrl();
        String url = homePageUrl+API_PATH+user.getUserId();
        String urlForHotel = homePageUrl+API_PATH_HOTEL;

        ArrayList ratings = restTemplate.exchange(url, HttpMethod.GET, null, ArrayList.class).getBody();

//        //ArrayList ratings = restTemplate.getForObject(url, ArrayList.class);
//
//        List<Rating> ratingList= (List<Rating>) ratings.stream().map(rating -> {
//            restTemplate.exchange(urlForHotel, HttpMethod.GET, null, ArrayList.class).getBody();
//            return rating;
//        }).collect(Collectors.toList());


        //user.setRatings(ratingList);
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
