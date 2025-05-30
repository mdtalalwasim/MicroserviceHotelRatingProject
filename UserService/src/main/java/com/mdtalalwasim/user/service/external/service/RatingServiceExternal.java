package com.mdtalalwasim.user.service.external.service;

import com.mdtalalwasim.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingServiceExternal {

    @GetMapping("/api/v1/ratings/users/{userId}")
    List<Rating> getRating(@PathVariable String userId);
}
