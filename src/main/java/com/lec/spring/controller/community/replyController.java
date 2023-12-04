package com.lec.spring.controller.community;

import com.lec.spring.service.community.ReplyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reply")
public class replyController {

    ReplyService replyService;

    public replyController(ReplyService replyService){
        this.replyService = replyService;
    }

    @PostMapping("/write")
    public String replyWrite(
            Long parentId,
            Long feedId,
            String content,
            Model model
    ) {
        System.out.println("reply Info : " + parentId);

        int result = replyService.addReplyByParentId(parentId, feedId, content);
        model.addAttribute("result", result);

        return "community/replyWriteOk";
    }

    @PostMapping("/delete")
    public String replyDelete(
            Long replyId,
            Model model
    ) {
        int result = replyService.deleteReplyById(replyId);
        model.addAttribute("result", result);

        return "community/replyDeleteOk";
    }

}
