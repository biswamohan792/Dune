package com.myHome.Dune.services.implementations;

import com.myHome.Dune.models.Channel;
import com.myHome.Dune.repositories.ChannelDao;
import com.myHome.Dune.services.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelDao dao;

    @Override
    public boolean addChannel(Channel channel) {
        try {
            dao.insert(channel);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateChannel(Channel channel) {
        try {
            if(dao.existsById(channel.getChannelId())) {
                dao.save(channel);
                return true;
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteChannel(Channel channel) {
        try {
            if(dao.existsById(channel.getChannelId())) {
                dao.delete(channel);
                return true;
            }
            return false;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
