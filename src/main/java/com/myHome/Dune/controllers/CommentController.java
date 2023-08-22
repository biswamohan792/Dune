package com.myHome.Dune.controllers;

import com.myHome.Dune.models.Comment;
import com.myHome.Dune.services.CommentService;
import com.myHome.Dune.utils.CommonUtils;
import com.myHome.Dune.utils.ErrorUtils;
import com.myHome.Dune.utils.ModelConverters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/dune")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/getAllComments")
    public Map<String,Object> getAllComments(@RequestParam Map<String,String> body){
        String videoId = body.get("videoId");
        String start = body.get("start");
        String end = body.get("end");
        if(CommonUtils.anyNull(videoId,start,end))
            return ErrorUtils.BAD_REQUEST();
        var comments = commentService.getComments(
                videoId,Integer.parseInt(start),Integer.parseInt(end)
        );
        return Map.of(
                "success",true,
                "body",comments
        );
    }

    @PostMapping("/comment")
    public Map<String,Object> addComment(@RequestParam Map<String,String> body){
        Comment comment = ModelConverters.createCommentFromMap(body);
        if(Objects.isNull(comment)) return ErrorUtils.BAD_REQUEST();
        if(commentService.addComment(comment))
            return Map.of(
                    "success",true,
                    "data",comment
            );
        return Map.of("success",false);
    }

    @PatchMapping("/comment")
    public Map<String,Object> updateComment(@RequestParam Map<String,String> body){
        Comment comment = ModelConverters.createCommentFromMap(body);
        if(Objects.isNull(comment)) return ErrorUtils.BAD_REQUEST();
        if(commentService.updateComment(comment))
            return Map.of(
                    "success",true,
                    "data",comment
            );
        return Map.of("success",false);
    }

    @DeleteMapping("/comment")
    public Map<String,Object> deleteComment(@RequestParam Map<String,String> body){
        Comment comment = ModelConverters.createCommentFromMap(body);
        if(Objects.isNull(comment)) return ErrorUtils.BAD_REQUEST();
        if(commentService.deleteComment(comment.getCommentId()))
            return Map.of(
                    "success",true
            );
        return Map.of("success",false);
    }
}
