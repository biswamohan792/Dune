package com.myHome.Dune.repositories;

import com.myHome.Dune.models.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentDao extends MongoRepository<Comment,String> {
    Page<Comment> findByVideoId(String videoId, Pageable pageable);
}
