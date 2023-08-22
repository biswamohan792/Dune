package com.myHome.Dune.services.implementations;

import com.myHome.Dune.models.Comment;
import com.myHome.Dune.repositories.CommentDao;
import com.myHome.Dune.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao dao;

    @Override
    public List<Comment> getComments(String videoId, int start, int end) {
        return dao.findByVideoId(
                videoId, PageRequest.of(start / (end - start), end - start)
        ).getContent();
    }

    @Override
    public boolean addComment(Comment comment) {
        try{
            dao.insert(comment);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateComment(Comment comment) {
        try{
            if(dao.existsById(comment.getCommentId())) {
                dao.save(comment);
                return true;
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteComment(String commentId) {
        try{
            if(dao.existsById(commentId)) {
                dao.deleteById(commentId);
                return true;
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
