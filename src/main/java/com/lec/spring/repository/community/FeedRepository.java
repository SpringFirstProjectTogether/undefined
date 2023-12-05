package com.lec.spring.repository.community;

import com.lec.spring.domain.community.FeedDTO;

import java.util.List;
import java.util.Set;

public interface FeedRepository {
    FeedDTO findFeedById(Long id);

    List<String> findTagsByFeedId(Long feedId);

    int saveFeed(FeedDTO feed);

    int deleteFeedById(Long feedId);

    Set<Long> feedIdListByNickname(String keyword);

    int updateFeed(FeedDTO feed);

    int deleteFeedAllByUserId(Long userId, String state);

    List<FeedDTO> findAllTempFeedByUserId(Long userId);


    
    // 상황에 따라 피드 글 수 반환 함수
    Long countAll();
    Long countAllByNickname(String nickname);
    Long countAllByTag(Set<Long> list);
    Long countFeedByUserId(Long userId, String state);

    // 상황에 따라 해당 페이지에 피드를 반환 하는 함수
    List<FeedDTO> findAllCompFeedFromRow(Integer fromRow, Integer pageRows);
    List<FeedDTO> listByNicknameFromRow(String nickname, Integer fromRow, Integer pageRows);
    List<FeedDTO> listByTagFromRow(Set<Long> feedIdList, Integer fromRow, Integer pageRows);
    List<FeedDTO> listByAllFromRow(Set<Long> feedIdList, Integer fromRow, Integer pageRows);

    List<FeedDTO> myFeedFromRow(Long userId, Integer fromRow, Integer pageRows, String state);

    // 기타 동작을 위한 메소드
    List<FeedDTO> findAllCompFeedByUserId(Long userId);


}
