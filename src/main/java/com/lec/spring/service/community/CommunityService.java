package com.lec.spring.service.community;

import com.lec.spring.domain.community.FeedDTO;

import java.util.List;

public interface CommunityService {
    List<FeedDTO> findAllCompFeed();

    List<FeedDTO> findAllCompFeedByNickname(String nickname);
    List<FeedDTO> findAllCompFeedByTag(String tag);

    List<FeedDTO> findAllCompFeedByAll(String keyword);

    List<String> findTagsByFeedId(Long feedId);

}
