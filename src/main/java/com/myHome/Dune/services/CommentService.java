package com.myHome.Dune.services;

import com.myHome.Dune.models.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getComments(String videoId, int start, int end);
    boolean addComment(Comment comment);
    boolean updateComment(Comment comment);
    boolean deleteComment(String commentId);
}
