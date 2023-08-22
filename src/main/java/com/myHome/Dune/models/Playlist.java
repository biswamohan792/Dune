package com.myHome.Dune.models;

import com.myHome.Dune.constants.MediaType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class Playlist {
    @Id
    private String playlistId;
    private String channelId;
    private String pic;
    private String name;
    private String desc;
    private Timestamp creation;
    private MediaType[] mediaTypes;
    private List<String> videoIds;
}
