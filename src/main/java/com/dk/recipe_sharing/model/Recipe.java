package com.dk.recipe_sharing.model;

import jakarta.persistence.*;
import lombok.*;
import com.dk.recipe_sharing.model.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @ManyToOne
    private User user;
    private String image;
    private String description;
    private boolean Vegetarian;
    private LocalDateTime createdAt;
    @ElementCollection
    private List<Long> likes=new ArrayList<>();

}
