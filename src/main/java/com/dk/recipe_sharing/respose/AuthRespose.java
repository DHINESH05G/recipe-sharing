package com.dk.recipe_sharing.respose;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthRespose {
    private String jwt;
    private  String message;
}
