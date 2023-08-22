package com.myHome.Dune.services.implementations;

import com.myHome.Dune.constants.PlaylistConstants;
import com.myHome.Dune.models.Playlist;
import com.myHome.Dune.models.Video;
import com.myHome.Dune.repositories.PlaylistDao;
import com.myHome.Dune.repositories.VideoDao;
import com.myHome.Dune.services.PlaylistManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistManagementServiceImpl implements PlaylistManagementService {
    @Autowired
    private PlaylistDao playlistDao;
    @Autowired
    private VideoDao videoDao;

    @Override
    public List<Playlist> getAllPlayLists(String channelId) {
        return playlistDao.findByChannelId(channelId);
    }

    @Override
    public boolean create(Playlist playlist) {
        try{
            playlistDao.insert(playlist);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }



    @Override
    public boolean update(Playlist playlist) {
        try{
            if (playlistDao.existsById(playlist.getPlaylistId())) {
                playlistDao.save(playlist);
                return true;
            }
            else return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteById(String playlistId) {
        try{
            if (playlistDao.existsById(playlistId)) {
                playlistDao.deleteById(playlistId);
                return true;
            }
            else return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addVideo(String playlistId,String videoId) {
        Optional<Playlist> playlistOptional = playlistDao.findById(playlistId);
        if (playlistOptional.isPresent()) {
            Playlist playlist = playlistOptional.get();
            playlist.getVideoIds().add(videoId);
            playlistDao.save(playlist);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String playlistId,String videoId) {
        Optional<Playlist> playlistOptional = playlistDao.findById(playlistId);
        if (playlistOptional.isPresent()) {
            Playlist playlist = playlistOptional.get();
            playlist.getVideoIds().remove(videoId);
            playlistDao.save(playlist);
            return true;
        }
        return false;
    }

    @Override
    public boolean addToFav(String channelId,String videoId) {
        Optional<Playlist> favoritesPlaylistOptional = playlistDao.findByChannelIdAndName(channelId, PlaylistConstants.FAVORITES);
        if (favoritesPlaylistOptional.isPresent()) {
            Playlist favoritesPlaylist = favoritesPlaylistOptional.get();
            favoritesPlaylist.getVideoIds().add(videoId);
            playlistDao.save(favoritesPlaylist);
            return true;
        }
        return false;
    }

    @Override
    public boolean addVideoInfo(Video video) {
        try {
            videoDao.insert(video);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteVideoInfo(String videoId) {
        try {
            if(videoDao.existsById(videoId)) {
                videoDao.deleteById(videoId);
                playlistDao.removeVideoFromPlaylists(videoId);
                return true;
            } else return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
