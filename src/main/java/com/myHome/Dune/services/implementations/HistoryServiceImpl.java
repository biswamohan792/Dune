package com.myHome.Dune.services.implementations;

import com.myHome.Dune.constants.RedisConstants;
import com.myHome.Dune.models.HistoryView;
import com.myHome.Dune.models.VideoWatchPoint;
import com.myHome.Dune.repositories.HistoryViewDao;
import com.myHome.Dune.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private HistoryViewDao dao;
    @Autowired
    private RedisTemplate<String, VideoWatchPoint> redisTemplate;
    @Autowired
    private ListOperations<String, VideoWatchPoint> listOps;

    @Override
    public List<HistoryView> getHistory(String userId, int page, int size) {
        return dao.findByUserId(userId, PageRequest.of(page, size, Sort.by("creation").descending()));
    }

    @Override
    public boolean deleteHistory(Duration duration) {
        try {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime thresholdDateTime = now.minus(duration);
            Timestamp nowTimestamp = Timestamp.valueOf(now);
            Timestamp threshold = Timestamp.valueOf(thresholdDateTime);
            dao.deleteByCreationBetween(threshold, nowTimestamp);
            return true; // Assuming success if no exceptions are thrown
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addHistory(HistoryView view) {
        try{
            dao.save(view);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<VideoWatchPoint> getHistoryPoints(List<String> videoIds, String userId) {
        return listOps.range(generateRedisKey(userId), 0, -1);
    }

    @Override
    public void addVideoWatchPoint(VideoWatchPoint point) {
        String redisKey = generateRedisKey(point.getUserId());
        listOps.leftPush(redisKey, point);
        // Trim the list to a maximum of 100 elements
        if(Double.compare(Math.random(),0.7)>0)
            listOps.trim(redisKey, 0, 99);
    }

    private String generateRedisKey(String userId) {
        return RedisConstants.REDIS_KEY_PREFIX_WATCH_POINT.concat(
                String.valueOf(userId.hashCode() & (Integer.MAX_VALUE - 1 ))
        );
    }


}
