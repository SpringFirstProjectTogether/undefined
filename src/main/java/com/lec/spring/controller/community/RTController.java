package com.lec.spring.controller.community;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lec.spring.domain.community.LikeDTO;
import com.lec.spring.repository.community.LikeRepository;
import com.lec.spring.service.community.FeedService;
import com.lec.spring.service.community.LikeService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/rest")
public class RTController {

    LikeService likeService;
    FeedService feedService;

    @Autowired
    public RTController(LikeService likeService, FeedService feedService) {
        this.likeService = likeService;
        this.feedService = feedService;
    }

    @RequestMapping("/like")
    public Integer like(
            @RequestBody String request
    ) throws JsonProcessingException {
        System.out.println("request : " + request);
        ObjectMapper mapper = new ObjectMapper();

        JsonNode node = mapper.readTree(request);
        
        // 현재는 User 1 로 고정

        if(node.get("isLike").asText().equals("true")) {
            likeService.addLike(1L, node.get("feedId").asLong());
        } else {
            likeService.removeLike(1L, node.get("feedId").asLong());
        }

        return likeService.countLike(node.get("feedId").asLong());
    }

    @PostMapping("/shortContent")
    public String shortContent(
            @RequestBody String request
    ) throws JsonProcessingException {
//        System.out.println("????");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(request);

        if(node.get("isShort").asBoolean()) {
            return feedService.findFeedById(node.get("feedId").asLong()).getShortContent();
        }
        return feedService.findFeedById(node.get("feedId").asLong()).getFeedContent();
    }
}
















