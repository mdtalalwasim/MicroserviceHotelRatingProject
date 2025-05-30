package com.mdtalalwasim.user.service.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Rating {
    private String ratingId;
    private String userId;
    private String hotelId;
    private int rating;
    private String feedback;

    private Hotel hotel;

}
