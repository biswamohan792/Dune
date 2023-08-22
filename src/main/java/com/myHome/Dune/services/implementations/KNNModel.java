package com.myHome.Dune.services.implementations;

import com.myHome.Dune.constants.MediaType;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class KNNModel {
    private static class Pair<T,V>{
        T t;
        V v;
        Pair(T t,V v){
            this.t = t;
            this.v = v;
        }
    }
    private final List<Pair<MediaType[],String>> dataItems;
    private final Map<MediaType,Integer> mediaIndex;
    private final MediaType[] mediaTypes;

    public KNNModel(){
        dataItems = new LinkedList<>();
        mediaTypes = MediaType.values();
        mediaIndex = new HashMap<>();
        for(int i=0;i<mediaTypes.length;i++)
            mediaIndex.put(mediaTypes[i],i);
    }
    public synchronized void fit(String videoId,MediaType[] types){
        dataItems.add(new Pair<MediaType[],String>(types,videoId));
    }

    public synchronized List<String> predict(MediaType[] types,int noOfPredictions){
        List<Pair<Double,String>> scoresList = new ArrayList<>();
        dataItems.forEach(item-> scoresList.add(new Pair<Double,String>(distance(item.t,types),item.v)));
        return scoresList.stream()
                .sorted(Comparator.comparing(a -> a.t))
                .limit(noOfPredictions)
                .map(e->e.v)
                .toList();
    }

    public synchronized void delete(String videoId){
        dataItems.removeIf((e)->e.v.equals(videoId));
    }

    private double distance(MediaType[] a,MediaType[] b){
        double res = 0;
        int[] no1 = new int[mediaTypes.length];
        int[] no2 = new int[mediaTypes.length];
        for(MediaType crawl:a) no1[mediaIndex.get(crawl)] = 2;
        for(MediaType crawl:b) no2[mediaIndex.get(crawl)] = 2;
        for(int i=0;i<mediaTypes.length;i++)
            res += Math.pow(no1[i]-no2[i],2);
        return Math.sqrt(res);
    }
}
