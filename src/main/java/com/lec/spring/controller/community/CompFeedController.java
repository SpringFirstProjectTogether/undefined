package com.lec.spring.controller.community;

import com.lec.spring.domain.community.FeedDTO;
import com.lec.spring.service.community.FeedService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/comp")
public class CompFeedController {

    FeedService feedService;

    public CompFeedController(FeedService feedService) {
        this.feedService = feedService;
    }

    // 완료글 목록 보여주기
    @RequestMapping("/list")
    public String list(Integer page, Model model) {
        feedService.listByUserId(1L, page, model, "comp");
        return "community/myCompFeed";
    }

    // 휴지통으로 이동
    @RequestMapping("/deleteAll")
    public String deleteAll(Model model) {
        int result = feedService.trashFeedAllByUserId(1L);
        model.addAttribute("result", result);

        return "community/compDeleteOk";
    }

}