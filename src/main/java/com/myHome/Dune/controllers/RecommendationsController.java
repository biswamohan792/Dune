package com.myHome.Dune.controllers;

import com.myHome.Dune.models.Video;
import com.myHome.Dune.services.RecommendationService;
import com.myHome.Dune.utils.CommonUtils;
import com.myHome.Dune.utils.ErrorUtils;
import com.myHome.Dune.utils.ModelConverters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/dune")
@CrossOrigin(value = "*")
public class RecommendationsController {
    @Autowired
    private RecommendationService recommendationService;

    @PostMapping("/recommendations")
    public Map<String,Object> getRecommendations(@RequestParam Map<String,String> body){
        String mediaTypes = body.get("mediaTypes");
        if(Objects.isNull(mediaTypes))
            return ErrorUtils.BAD_REQUEST();
        return Map.of(
            "success","true",
            "body",recommendationService.getRecommendations(
                        Video.builder()
                                .mediaTypes(ModelConverters.parseMediaTypes(mediaTypes))
                                .build()
                )
        );
    }

    @PutMapping("/recommendations")
    public Map<String,Object> addRecommendations(@RequestParam Map<String,String> body){
        String mediaTypes = body.get("mediaTypes");
        String videoId = body.get("videoId");
        if(CommonUtils.anyNull(mediaTypes,videoId))
            return ErrorUtils.BAD_REQUEST();
        recommendationService.addVideoDataForProcessing(
                Video.builder()
                        .videoId(videoId)
                        .mediaTypes(ModelConverters.parseMediaTypes(mediaTypes))
                        .build());
        return Map.of("success","true");
    }

    @DeleteMapping("/recommendations")
    public Map<String,Object> deleteRecommendations(@RequestParam Map<String,String> body){
        String videoId = body.get("videoId");
        if(Objects.isNull(videoId))
            return ErrorUtils.BAD_REQUEST();
        recommendationService.deleteVideoData(videoId);
        return Map.of("success",true);
    }
}
