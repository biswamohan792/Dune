package com.myHome.Dune.services.implementations;

import com.myHome.Dune.constants.RecommendationConstants;
import com.myHome.Dune.models.Video;
import com.myHome.Dune.repositories.VideoDao;
import com.myHome.Dune.services.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {
    @Autowired
    private VideoDao videoDao;
    @Autowired
    private KNNModel knnModel;
    @Override
    public List<Video> getRecommendations(Video video) {
        return videosFromIds(knnModel.predict(
                video.getMediaTypes(),
                RecommendationConstants.NO_OF_PREDICTIONS
        ));
    }

    @Override
    public void addVideoDataForProcessing(Video video) {
        knnModel.fit(video.getVideoId(), video.getMediaTypes());
    }

    @Override
    public void deleteVideoData(String videoId) {
        knnModel.delete(videoId);
    }

    private List<Video> videosFromIds(List<String> ids){
        return videoDao.findAllById(ids);
    }
}
