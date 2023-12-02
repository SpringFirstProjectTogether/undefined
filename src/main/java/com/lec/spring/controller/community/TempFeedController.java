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
    public String list(Model model) {
        List<FeedDTO> list = feedService.findAllTempFeedByUserId(1L);

        model.addAttribute("list", list);
        model.addAttribute("totalCnt", list == null ? 0 : list.size());

        return "community/myTempFeed";
    }



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
