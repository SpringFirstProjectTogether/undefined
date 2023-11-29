package com.lec.spring.repository.community;

import com.lec.spring.domain.community.FeedDTO;

import java.util.List;

public interface CommunityRepository {

    List<FeedDTO> findAllCompFeed();

    List<FeedDTO> findAllCompFeedByNickname(String nickname);

    List<FeedDTO> findAllCompFeedByTag(String tag);

    List<FeedDTO> findAllCompFeedByAll(String keyword);

    List<String> findTagsByFeedId(Long feedId);
}
