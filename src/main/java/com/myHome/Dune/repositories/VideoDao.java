package com.myHome.Dune.repositories;

import com.myHome.Dune.models.Video;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VideoDao extends MongoRepository<Video,String> { }
