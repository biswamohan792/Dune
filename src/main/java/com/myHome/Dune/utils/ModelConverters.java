package com.myHome.Dune.utils;

import com.myHome.Dune.constants.MediaType;
import com.myHome.Dune.models.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Map;

public class ModelConverters {
    public static Channel createChannelFromMap(Map<String,String> body){
        String name = body.get("name");
        String desc = body.get("description");
        String pic = body.get("pic");
        String userId = body.get("userId");
        if(CommonUtils.anyNull(
                name,desc,pic,userId
        )) return null;
        return Channel.builder().channelId(CommonUtils.genRandomId(10))
                .userId(userId)
                .creation(Timestamp.from(Instant.now()))
                .pic(pic)
                .desc(desc)
                .name(name)
                .build();
    }

    public static Comment createCommentFromMap(Map<String,String> body){
        String comment = body.get("comment");
        String commentId = body.get("commentId");
        String videoId = body.get("videoId");
        String userId = body.get("userId");
        String parentId = body.get("parentId");
        String level = body.get("level");
        if(CommonUtils.anyNull(
                comment,commentId,videoId,userId,parentId,level
        )) return null;
        return Comment.builder()
                .comment(comment)
                .commentId(commentId)
                .creation(Timestamp.from(Instant.now()))
                .videoId(videoId)
                .userId(userId)
                .parentId(parentId)
                .level(Integer.parseInt(level))
                .build();
    }

    public static Playlist createPlaylistFromMap(Map<String,String> body){
        String name = body.get("name");
        String channelId = body.get("channelId");
        String pic = body.get("pic");
        String desc = body.get("desc");
        MediaType[] mediaTypes = parseMediaTypes(body.get("mediaTypes"));
        if (CommonUtils.anyNull(name, channelId, pic, desc)) return null;
        return Playlist.builder()
                .name(name)
                .playlistId(CommonUtils.genRandomId(11))
                .channelId(channelId)
                .pic(pic)
                .desc(desc)
                .mediaTypes(mediaTypes)
                .videoIds(List.of())
                .creation(Timestamp.from(Instant.now()))
                .build();
    }

    public static MediaType[] parseMediaTypes(String mediaTypes) {
        String[] mediaTypeStrings = mediaTypes.split(",");
        MediaType[] parsedMediaTypes = new MediaType[mediaTypeStrings.length];
        for (int i = 0; i < mediaTypeStrings.length; i++) {
            try {
                parsedMediaTypes[i] = MediaType.valueOf(mediaTypeStrings[i].trim());
            } catch (IllegalArgumentException e) {
                parsedMediaTypes[i] = MediaType.COMEDY; // Default value
            }
        }
        return parsedMediaTypes;
    }

    public static Video createVideoFromMap(Map<String, String> body) {
        String name = body.get("name");
        String playlistId = body.get("playlistId");
        String url = body.get("url");
        String pic = body.get("pic");
        String desc = body.get("desc");
        int no = Integer.parseInt(body.get("no"));
        String videoId = body.get("videoId");
        MediaType[] mediaTypes = parseMediaTypes(body.get("mediaTypes"));
        if (CommonUtils.anyNull(name, playlistId, url, pic, desc, videoId))
            return null;
        return Video.builder()
                .name(name)
                .playlistId(playlistId)
                .url(url)
                .pic(pic)
                .desc(desc)
                .no(no)
                .videoId(videoId)
                .creation(Timestamp.from(Instant.now()))
                .mediaTypes(mediaTypes)
                .build();
    }

    public static HistoryView createHistoryViewFromMap(Map<String,String> body){
        String videoId = body.get("videoId");
        String userId = body.get("userId");
        if(CommonUtils.anyNull(
                videoId,userId
        )) return null;
        return HistoryView.builder()
                .userId(userId)
                .videoId(videoId)
                .creation(Timestamp.from(Instant.now()))
                .build();
    }

    public static VideoWatchPoint createVideoWatchPointFromMap(Map<String,String> body){
        String userId = body.get("userId");
        String videoId = body.get("videoId");
        String watched = body.get("watched");
        if(CommonUtils.anyNull(userId,videoId,watched))
            return null;
        return VideoWatchPoint.builder()
                .watched(watched)
                .videoId(videoId)
                .userId(userId)
                .build();
    }
}
