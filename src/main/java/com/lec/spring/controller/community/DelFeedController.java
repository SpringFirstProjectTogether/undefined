package com.lec.spring.controller.community;

import com.lec.spring.domain.community.FeedDTO;
import com.lec.spring.service.community.FeedService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/trash")
public class DelFeedController {

    FeedService feedService;

    public DelFeedController(FeedService feedService) {
        this.feedService = feedService;
    }

    @RequestMapping("/list")
    public String list(Model model) {
        List<FeedDTO> list = feedService.findAllDelFeedByUserId(1L);

        model.addAttribute("list", list);
        model.addAttribute("totalCnt", list == null ? 0 : list.size());

        return "community/myDelFeed";
    }

    // 휴지통 비우기
    @RequestMapping("/deleteAll")
    public String deleteAll(Model model) {
        int result = feedService.deleteFeedAllByUserId(1L);
        model.addAttribute(result);

        return "community/deleteAllOk";
    }

    // 복원
    @PostMapping("/restore")
    public String restore(
            Long feedId,
            Model model)
    {
        int result = feedService.restoreFeed(feedId);
        model.addAttribute("result", result);

        return "community/restoreOk";
    }
    
    // 완전 삭제
    @PostMapping("/delete")
    public String delete(
            Long feedId,
            Model model
    ) {
        int result = feedService.deleteFeed(feedId);
        model.addAttribute("result", result);

        return "community/communityDeleteOk";
    }
}
