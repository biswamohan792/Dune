package com.myHome.Dune.repositories;

import com.myHome.Dune.models.HistoryView;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.sql.Timestamp;
import java.util.List;

public interface HistoryViewDao extends MongoRepository<HistoryView,String> {
    List<HistoryView> findByUserId(String userId, PageRequest pageRequest);
    long deleteByCreationBetween(Timestamp start,Timestamp end);
}
