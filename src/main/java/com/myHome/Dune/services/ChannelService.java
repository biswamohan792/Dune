package com.myHome.Dune.services;

import com.myHome.Dune.models.Channel;

public interface ChannelService {
    boolean addChannel(Channel channel);
    boolean updateChannel(Channel channel);
    boolean deleteChannel(Channel channel);
}
