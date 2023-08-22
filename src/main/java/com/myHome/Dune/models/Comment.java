package com.myHome.Dune.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

@Builder
@Data
public class Comment {
    @Id
    private String commentId;
    private String comment;
    private String videoId;
    private String userId;
    private int level;
    private String parentId;
    private Timestamp creation;
}
