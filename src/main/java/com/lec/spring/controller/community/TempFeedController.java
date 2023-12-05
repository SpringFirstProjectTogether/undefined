package com.lec.spring.controller.community;

import com.lec.spring.domain.community.FeedDTO;
import com.lec.spring.service.community.FeedService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/temp")
public class TempFeedController {

    FeedService feedService;

    public TempFeedController(FeedService feedService) {
        this.feedService = feedService;
    }


    @RequestMapping("/list")
    public String list(Integer page, Model model) {
        feedService.listByUserId(1L, page, model, "temp");
        return "community/myTempFeed";
    }

    @RequestMapping("/deleteAll")
    public String deleteAll(Model model) {
        int result = feedService.deleteFeedAllByUserId(1L, "temp");
        model.addAttribute("result", result);

        return "community/tempDeleteOk";
    }


    @PostMapping("/delete")
    public String delete(
            Long feedId,
            Model model
    ) {
        int result = feedService.deleteFeed(feedId);
        model.addAttribute("result", result);

        return "community/tempDeleteOk";
    }

}
