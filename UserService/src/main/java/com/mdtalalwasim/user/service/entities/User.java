package com.mdtalalwasim.user.service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "micro_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User {

    @Id
    private String userId;
    private String name;
    private String email;
    private String about;

    @Transient //dont store in database
    private List<Rating> ratings=new ArrayList<>();

}
