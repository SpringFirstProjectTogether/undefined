package com.lec.spring.service.community;

import com.lec.spring.domain.community.FeedDTO;
import com.lec.spring.domain.community.ReplyDTO;
import com.lec.spring.domain.community.CommentDTO;
import org.springframework.ui.Model;

import java.util.List;

public interface FeedService {

    int writeFeed(FeedDTO feed);
    int deleteFeed(Long feedId);
    int updateFeed(FeedDTO feed);
    int trashFeed(Long feedId);
    FeedDTO findFeedById(Long id);
    int trashFeedAllByUserId(Long userId);
    int deleteFeedAllByUserId(Long userId, String state);
    int restoreFeed(Long feedId);


    // 리스트 함수
    List<FeedDTO> list(Integer page, Model model);
    List<FeedDTO> listByNickname(String nickname, Integer page, Model model);
    List<FeedDTO> listByTag(String nickname, Integer page, Model model);
    List<FeedDTO> listByAll(String keyword, Integer page, Model model);
    void listByOption(String option, String keyword, Integer page, Model model);

    // User Id 별 리스트 함수
    List<FeedDTO> listByUserId(Long userId, Integer page, Model model, String state);
}
