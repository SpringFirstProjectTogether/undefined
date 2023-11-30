package com.lec.spring.controller.community;

import com.lec.spring.domain.community.FeedDTO;
import com.lec.spring.service.community.CommunityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/community")
public class CommunityController {

    CommunityService communityService;

    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @RequestMapping("/list")
    public String list(Model model) {
        LocalDateTime start = LocalDateTime.now();
        List<FeedDTO> list = communityService.findAllCompFeed();
        LocalDateTime end = LocalDateTime.now();

        model.addAttribute("list", list);
        model.addAttribute("option", "all");
        model.addAttribute("totalCnt", list == null ? 0 : list.size());

        Duration diff = Duration.between(start.toLocalTime(), end.toLocalTime());
        model.addAttribute("searchTime", diff.toMillis() / 1000.00);

        return "community/communityMainPage";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam String searchOption
            , @RequestParam String keyword
            , Model model
    ) {

        LocalDateTime start = LocalDateTime.now();
        List<FeedDTO> list = null;
        if(!keyword.isEmpty()) {
            list = switch (searchOption) {
                case "nick" -> communityService.findAllCompFeedByNickname(keyword);
                case "tag" -> communityService.findAllCompFeedByTag(keyword);
                default -> communityService.findAllCompFeedByAll(keyword);
            };
            list.forEach(System.out::println);
        } else {
            list = communityService.findAllCompFeed();
            searchOption = "all";
        }
        LocalDateTime end = LocalDateTime.now();

        model.addAttribute("totalCnt", list == null ? 0 : list.size());
        model.addAttribute("list", list);
        model.addAttribute("option", searchOption);

        Duration diff = Duration.between(start.toLocalTime(), end.toLocalTime());
        model.addAttribute("searchTime", diff.toMillis() / 1000.00);

        return "community/communityMainPage";
    }

    @GetMapping("/write")
    public String write() {
        return "community/communityWrite";
    }

    @PostMapping("/write")
    public String writeOk(
//            @ResponseBody
    ) {

        return "community/writeOk";
    }
}
