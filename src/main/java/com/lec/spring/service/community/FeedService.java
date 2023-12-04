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

    List<FeedDTO> findAllCompFeedByUserId(Long userId);

    int trashFeedAllByUserId(Long userId);

    List<FeedDTO> findAllDelFeedByUserId(Long userId);

    int deleteFeedAllByUserId(Long userId);

    int restoreFeed(Long feedId);

    List<FeedDTO> findAllTempFeedByUserId(Long userId);



    // 리스트 함수
    List<FeedDTO> list(Integer page, Model model);
    List<FeedDTO> listByNickname(String nickname, Integer page, Model model);
    List<FeedDTO> listByTag(String nickname, Integer page, Model model);
    List<FeedDTO> listByAll(String keyword, Integer page, Model model);
}
