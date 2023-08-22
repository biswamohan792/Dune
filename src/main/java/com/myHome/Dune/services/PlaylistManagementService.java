package com.myHome.Dune.services;

import com.myHome.Dune.models.Playlist;
import com.myHome.Dune.models.Video;

import java.util.List;

public interface PlaylistManagementService {
    List<Playlist> getAllPlayLists(String channelId);
    boolean create(Playlist playlist);
    boolean update(Playlist playlist);
    boolean deleteById(String playlistId);
    boolean addVideo(String playlistId, String videoId);
    boolean delete(String playlistId, String videoId);
    boolean addToFav(String channelId,String videoId);
    boolean addVideoInfo(Video video);
    boolean deleteVideoInfo(String videoId);

}
