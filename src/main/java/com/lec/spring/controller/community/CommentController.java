package com.lec.spring.controller.community;

import com.lec.spring.service.community.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comment")
public class CommentController {

    CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/write")
    public String commentWrite(
            Long feedId,
            String content,
            Model model
    ) {

        int result = commentService.addCommentByFeedId(feedId, content);
        model.addAttribute("result", result);

        return "community/commentWriteOk";
    }

    @PostMapping("/delete")
    public String commentDelete(
        Long commentId,
        Model model
    ) {

        int result = commentService.deleteCommentById(commentId);
        model.addAttribute("result", result);

        return "community/commentDeleteOk";
    }

}
