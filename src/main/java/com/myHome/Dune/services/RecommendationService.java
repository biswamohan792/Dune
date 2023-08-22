package com.myHome.Dune.services;

import com.myHome.Dune.models.Video;

import java.util.List;

public interface RecommendationService {
    List<Video> getRecommendations(Video video);
    void addVideoDataForProcessing(Video video);
    void deleteVideoData(String videoId);
}
