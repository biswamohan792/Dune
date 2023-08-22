package com.myHome.Dune.controllers;

import com.myHome.Dune.models.Channel;
import com.myHome.Dune.services.ChannelService;
import com.myHome.Dune.utils.ErrorUtils;
import com.myHome.Dune.utils.ModelConverters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/dune")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @PostMapping("/channel")
    public Map<String,Object> createChannel(@RequestParam Map<String,String> body){
        Channel channel = ModelConverters.createChannelFromMap(body);
        if(Objects.isNull(channel)) return ErrorUtils.BAD_REQUEST();
        if(channelService.addChannel(channel)) return Map.of(
                "success",true,
                "data",Map.of(
                        "channelId",channel.getChannelId(),
                        "name",channel.getName(),
                        "description",channel.getDesc(),
                        "pic",channel.getPic()
                )
        );
        return ErrorUtils.INTERNAL_SERVER_ERROR();
    }

    @PatchMapping("/channel")
    public Map<String,Object> updateChannel(@RequestParam Map<String,String> body){
        Channel channel = ModelConverters.createChannelFromMap(body);
        if(Objects.isNull(channel)) return ErrorUtils.BAD_REQUEST();
        if(channelService.updateChannel(channel)) return Map.of(
                "success",true,
                "data",Map.of(
                        "channelId",channel.getChannelId(),
                        "name",channel.getName(),
                        "description",channel.getDesc(),
                        "pic",channel.getPic()
                )
        );
        return ErrorUtils.INTERNAL_SERVER_ERROR();
    }

    @DeleteMapping("/channel")
    public Map<String,Object> deleteChannel(@RequestParam Map<String,String> body){
        Channel channel = ModelConverters.createChannelFromMap(body);
        if(Objects.isNull(channel)) return ErrorUtils.BAD_REQUEST();
        if(channelService.deleteChannel(channel)) return Map.of("success",true);
        return ErrorUtils.INTERNAL_SERVER_ERROR();
    }




}
