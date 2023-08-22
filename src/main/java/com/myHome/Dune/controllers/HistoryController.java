package com.myHome.Dune.controllers;


import com.myHome.Dune.models.HistoryView;
import com.myHome.Dune.models.VideoWatchPoint;
import com.myHome.Dune.services.HistoryService;
import com.myHome.Dune.utils.CommonUtils;
import com.myHome.Dune.utils.ErrorUtils;
import com.myHome.Dune.utils.ModelConverters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@RequestMapping("/dune")
@CrossOrigin(value = "*")
@RestController
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    @PostMapping("/history")
    public Map<String,Object> getHistory(@RequestParam Map<String,String> body){
        String userId = body.get("userId");
        String page = body.get("page");
        String size = body.get("size");
        if(CommonUtils.anyNull(
                userId,page,size
        )) return ErrorUtils.BAD_REQUEST();
        return Map.of(
                "success",true,
                "body",historyService.getHistory(
                        userId,
                        Integer.parseInt(page),
                        Integer.parseInt(size)
                )
        );
    }

    @PutMapping("/history")
    public Map<String,Object> addHistory(@RequestParam Map<String,String> body){
        HistoryView view = ModelConverters.createHistoryViewFromMap(body);
        if(Objects.isNull(view)) return ErrorUtils.BAD_REQUEST();
        return Map.of("success",historyService.addHistory(view));
    }

    @DeleteMapping("/history")
    public Map<String, Object> deleteHistory(@RequestParam Map<String,String> body) {
        String _duration = body.get("duration");
        if(CommonUtils.anyNull(_duration)) return ErrorUtils.BAD_REQUEST();
        Duration duration = Duration.ofHours(Long.parseLong(_duration));
        if (historyService.deleteHistory(duration))
            return Map.of("success", true, "body", "History entries deleted successfully.");
        else return ErrorUtils.INTERNAL_SERVER_ERROR();
    }

    @PostMapping("/watchPoints")
    public Map<String,Object> getWatchPoints(@RequestParam Map<String,String> body){
        String videoIds = body.get("videoIds");
        String userId = body.get("userId");
        if(CommonUtils.anyNull(videoIds,userId))
            return ErrorUtils.BAD_REQUEST();
        return Map.of(
                "success",true,
                "body",historyService.getHistoryPoints(Arrays.asList(videoIds.split(",")),userId)
        );
    }

    @PutMapping("/watchPoints")
    public Map<String,Object> addWatchPoints(@RequestParam Map<String,String> body){
        VideoWatchPoint videoWatchPoint = ModelConverters.createVideoWatchPointFromMap(body);
        if(Objects.isNull(videoWatchPoint)) return ErrorUtils.BAD_REQUEST();
        historyService.addVideoWatchPoint(videoWatchPoint);
        return Map.of("success",true);
    }

}
