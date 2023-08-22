package com.myHome.Dune.repositories;

import com.myHome.Dune.models.Channel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChannelDao extends MongoRepository<Channel,String> { }
