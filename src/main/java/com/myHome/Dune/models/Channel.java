package com.myHome.Dune.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

@Data
@Builder
public class Channel {
    private String userId;
    @Id
    private String channelId;
    private String name;
    private String desc;
    private String pic;
    private Timestamp creation;
}
