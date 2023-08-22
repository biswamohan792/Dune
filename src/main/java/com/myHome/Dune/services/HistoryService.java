package com.myHome.Dune.services;

import com.myHome.Dune.models.HistoryView;
import com.myHome.Dune.models.VideoWatchPoint;

import java.time.Duration;
import java.util.List;

public interface HistoryService {
    List<HistoryView> getHistory(String userId,int page,int size);
    boolean deleteHistory(Duration duration);
    boolean addHistory(HistoryView view);
    List<VideoWatchPoint> getHistoryPoints(List<String> videoIds,String userId);
    void addVideoWatchPoint(VideoWatchPoint point);
}
