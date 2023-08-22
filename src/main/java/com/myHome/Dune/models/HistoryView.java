package com.myHome.Dune.models;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class HistoryView {
    private String videoId;
    private String userId;
    private Timestamp creation;
}
