package com.myHome.Dune.repositories;

import com.myHome.Dune.models.Playlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlaylistDao extends MongoRepository<Playlist,String> {
    List<Playlist> findByChannelId(String channelId);
    Optional<Playlist> findByChannelIdAndName(String channelId, String name);
    List<Playlist> findByVideoIdsContaining(String videoId);
    @Query(value = "{'videoIds': ?0}", delete = true)
    void removeVideoFromPlaylists(String videoId);
}
