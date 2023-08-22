package com.myHome.Dune.models;

import com.myHome.Dune.constants.MediaType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

@Data
@Builder
public class Video {
    private String playlistId;
    private String url;
    private String name;
    private String pic;
    private String desc;
    private int no;
    @Id
    private String videoId;
    private Timestamp creation;
    private MediaType[] mediaTypes;
}
