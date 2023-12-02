package com.lec.spring.controller.community;

import com.lec.spring.domain.community.FeedDTO;
import com.lec.spring.service.community.FeedService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/community")
public class CommunityController {

    FeedService feedService;

    public CommunityController(FeedService feedService) {
        this.feedService = feedService;
    }

    @RequestMapping("/list")
    public String list(Model model) {
        LocalDateTime start = LocalDateTime.now();
        List<FeedDTO> list = feedService.findAllCompFeed();
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
                case "nick" -> feedService.findAllCompFeedByNickname(keyword);
                case "tag" -> feedService.findAllCompFeedByTag(keyword);
                default -> feedService.findAllCompFeedByAll(keyword);
            };
            list.forEach(System.out::println);
        } else {
            list = feedService.findAllCompFeed();
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
//            @RequestParam Map<String, MultipartFile> files   // 첨부 파일
//            , BindingResult result
            FeedDTO feed
            , Model model   // 매개변수 선언시 BindingResult 보다 Model 을 뒤에 두어야 한다.
//            , RedirectAttributes redirectAttrs
    ){
        // validation 에러가 있었다면 redirect 할거다!
//        if(result.hasErrors()){
//            redirectAttrs.addFlashAttribute("subject", post.getSubject());
//            redirectAttrs.addFlashAttribute("content", post.getContent());
//
//            for(var err : result.getFieldErrors()){
//                redirectAttrs.addFlashAttribute("error_" + err.getField(), err.getCode());
//            }
//
//            return "redirect:/board/write";
//        }

        // add 할 때 어떤 작업이 필요??
        model.addAttribute("result", feedService.writeFeed(feed));

        if(feed.getFeedState().equals("comp"))
            return "community/writeCompOk";

        return "community/writeTempOk";
    }

    @GetMapping("/update/{id}")
    public String update(
            @PathVariable Long id,
            Model model
    ) {
        FeedDTO feed = feedService.findFeedById(id);

        // 태그 앞에 # 없애주기
        if(!feed.getTagList().isEmpty())
            feed.setTagList(feed.getTagList().replaceAll(" #", ", ").substring(1));

        model.addAttribute("feed", feed);

        return "community/communityUpdate";
    }

    @PostMapping("/update")
    public String updateOk(
//            @RequestParam Map<String, MultipartFile> files  // 새로 추가될 첨부파일들
//            , Long[] delfile    // 삭제될 파일들
//            , @Valid Post post
//            , BindingResult result
            FeedDTO feed
            , Model model
//            , RedirectAttributes redirectAttrs
    ){
//        if(result.hasErrors()){
//
//            redirectAttrs.addFlashAttribute("subject", post.getSubject());
//            redirectAttrs.addFlashAttribute("content", post.getContent());
//
//            for(var err : result.getFieldErrors()){
//                redirectAttrs.addFlashAttribute("error_" + err.getField(), err.getCode());
//            }
//
//            return "redirect:/board/update/" + post.getId();
//        }


        model.addAttribute("result", feedService.updateFeed(feed));
        return "community/communityUpdateOk";
    }

    @PostMapping("/delete")
    public String delete(
            Long feedId,
            Model model
    ) {
        int result = feedService.trashFeed(feedId);
        model.addAttribute("result", result);

        return "community/communityDeleteOk";
    }



}
