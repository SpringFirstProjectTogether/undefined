package com.lec.spring.controller.community;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lec.spring.domain.community.LikeDTO;
import com.lec.spring.repository.community.LikeRepository;
import com.lec.spring.service.community.LikeService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/rest")
public class RTController {
    private static final String HTTPBIN_URL = "https://httpbin.org";

    LikeService likeService;

    public RTController(LikeService likeService) {
        this.likeService = likeService;
    }

    @RequestMapping("/like")
    public Integer apiTest10(
            @RequestBody String request,
            Model model
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
}
















