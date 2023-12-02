package com.lec.spring.repository.community;

import com.lec.spring.domain.community.FeedDTO;

import java.util.List;
import java.util.Set;

public interface FeedRepository {
    FeedDTO findFeedById(Long id);

    List<FeedDTO> findAllCompFeed();

    List<FeedDTO> findAllCompFeedByNickname(String nickname);

    List<FeedDTO> findAllCompFeedByTag(Set<Long> feedIdList);

    List<FeedDTO> findAllCompFeedByAll(Set<Long> feedIdList);

    List<String> findTagsByFeedId(Long feedId);

    int saveFeed(FeedDTO feed);

    int deleteFeedById(Long feedId);

    Set<Long> feedIdListByNickname(String keyword);

    int updateFeed(FeedDTO feed);

    List<FeedDTO> findAllCompFeedByUserId(Long userId);

    List<FeedDTO> findAllDelFeedByUserId(Long userId);

    int deleteFeedAllByUesrId(Long userId);

    List<FeedDTO> findAllTempFeedByUserId(Long userId);
}
