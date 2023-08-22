package com.myHome.Dune.models;

import com.myHome.Dune.constants.Gender;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

@Builder
@Data
public class User {
    @Id
    private String userId;
    private String name;
    private String pic;
    private String password;
    private String email;
    private String details;
    private int age;
    private Timestamp creation;
    private String phone;
    private Gender gender;
}
