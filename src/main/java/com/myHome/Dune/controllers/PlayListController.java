package com.myHome.Dune.controllers;

import com.myHome.Dune.models.Playlist;
import com.myHome.Dune.models.Video;
import com.myHome.Dune.services.PlaylistManagementService;
import com.myHome.Dune.services.RecommendationService;
import com.myHome.Dune.utils.CommonUtils;
import com.myHome.Dune.utils.ErrorUtils;
import com.myHome.Dune.utils.ModelConverters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Objects;

public class PlayListController {
    @Autowired
    private PlaylistManagementService playlistManagementService;
    @Autowired
    private RecommendationService recommendationService;

    @PostMapping("/getAllPlayLists")
    public Map<String,Object> getAllPlayLists(@RequestParam Map<String,String> body){
        String channelId = body.get("channelId");
        if(CommonUtils.anyNull(channelId)) return ErrorUtils.BAD_REQUEST();
        var playlists = playlistManagementService.getAllPlayLists(channelId);
        return Map.of(
                "success",true,
                "body",playlists
        );
    }

    @PostMapping("/playlist")
    public Map<String,Object> createPlayList(@RequestParam Map<String,String> body){
        Playlist playlist = ModelConverters.createPlaylistFromMap(body);
        if(Objects.isNull(playlist)) return ErrorUtils.BAD_REQUEST();
        if (playlistManagementService.create(playlist))
            return Map.of("success", true, "body", "Playlist created successfully");
        else return ErrorUtils.INTERNAL_SERVER_ERROR();
    }

    @PatchMapping("/playlist")
    public Map<String,Object> updatePlayList(@RequestParam Map<String,String> body){
        Playlist playlist = ModelConverters.createPlaylistFromMap(body);
        if(Objects.isNull(playlist)) return ErrorUtils.BAD_REQUEST();
        if (playlistManagementService.update(playlist))
            return Map.of("success", true, "body", "Playlist updated successfully");
        else return ErrorUtils.INTERNAL_SERVER_ERROR();
    }

    @DeleteMapping("/playlist")
    public Map<String,Object> deletePlayList(@RequestParam Map<String,String> body){
        String playlistId = body.get("playlistId");
        if (CommonUtils.anyNull(playlistId))
            return ErrorUtils.BAD_REQUEST();
        if (playlistManagementService.deleteById(playlistId))
            return Map.of("success", true, "body", "Playlist deleted successfully");
        else return ErrorUtils.INTERNAL_SERVER_ERROR();
    }

    @PostMapping("/addVideoToPlayList")
    public Map<String, Object> addVideoToPlaylist(@RequestParam Map<String, String> body) {
        String playlistId = body.get("playlistId");
        String videoId = body.get("videoId");
        if (CommonUtils.anyNull(playlistId, videoId)) return ErrorUtils.BAD_REQUEST();
        if (playlistManagementService.addVideo(playlistId, videoId))
            return Map.of("success", true, "body", "Video added to playlist successfully");
        else return ErrorUtils.INTERNAL_SERVER_ERROR();
    }

    @PostMapping("/deleteVideoFromPlayList")
    public Map<String, Object> deleteVideoFromPlaylist(@RequestParam Map<String, String> body) {
        String playlistId = body.get("playlistId");
        String videoId = body.get("videoId");
        if (CommonUtils.anyNull(playlistId, videoId)) return ErrorUtils.BAD_REQUEST();
        if ( playlistManagementService.delete(playlistId, videoId) )
            return Map.of("success", true, "body", "Video deleted from playlist successfully");
        else return ErrorUtils.INTERNAL_SERVER_ERROR();
    }

    @PostMapping("/addToFav")
    public Map<String, Object> addToFavoritesPlaylist(@RequestParam Map<String, String> body) {
        String channelId = body.get("channelId");
        String videoId = body.get("videoId");
        if (CommonUtils.anyNull(channelId, videoId)) return ErrorUtils.BAD_REQUEST();
        if (playlistManagementService.addToFav(channelId, videoId))
            return Map.of("success", true, "body", "Video added to favorites successfully");
        else return ErrorUtils.INTERNAL_SERVER_ERROR();
    }

    @PostMapping("/videoInfo")
    public Map<String, Object> createVideoInfo(@RequestParam Map<String, String> body) {
        Video video = ModelConverters.createVideoFromMap(body);
        if (Objects.isNull(video)) return ErrorUtils.BAD_REQUEST();
        if (playlistManagementService.addVideoInfo(video)) {
            recommendationService.addVideoDataForProcessing(video);
            return Map.of("success", true, "body", "Video info created successfully");
        }
        else return ErrorUtils.INTERNAL_SERVER_ERROR();
    }

    @DeleteMapping("/videoInfo")
    public Map<String,Object> deleteVideoInfo(@RequestParam Map<String,String> body){
        String videoId = body.get("videoId");
        if (CommonUtils.anyNull(videoId)) return ErrorUtils.BAD_REQUEST();
        if (playlistManagementService.deleteVideoInfo(videoId)) {
            recommendationService.deleteVideoData(videoId);
            return Map.of("success", true, "body", "Video info deleted successfully");
        }
        else return ErrorUtils.INTERNAL_SERVER_ERROR();
    }
}
