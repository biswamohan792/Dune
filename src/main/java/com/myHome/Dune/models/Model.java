package com.myHome.Dune.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

@Data
@Builder
public class Model {
    private User user;
    private String name;
    private String desc;
    private String pic;
    @Id
    private String channelId;
    private Timestamp creation;
}
