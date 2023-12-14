package com.lec.spring.controller.community;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lec.spring.domain.community.QryCommentList;
import com.lec.spring.domain.community.QryResult;
import com.lec.spring.service.community.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/list")
    public QryCommentList list(Long feedId){
        return commentService.list(feedId);
    }

    @PostMapping("/write")
    public QryResult commentWrite(
            Long feedId,
            String content,
            Long parentId,
            Long userId
    ) {
        System.out.println(feedId + " " + content + " " + parentId + " " + userId);
        return commentService.addCommentByFeedId(feedId, parentId, userId, content);
    }

    @PostMapping("/delete")
    public QryResult commentDelete(Long commentId) {
        return commentService.deleteCommentById(commentId);
    }


}
