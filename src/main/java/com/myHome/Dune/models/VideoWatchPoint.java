package com.myHome.Dune.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VideoWatchPoint {
    private String userId;
    private String videoId;
    private String watched;
}
