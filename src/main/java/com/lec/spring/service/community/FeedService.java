package com.lec.spring.service.community;

import com.lec.spring.domain.community.FeedDTO;
import com.lec.spring.domain.community.ReplyDTO;
import com.lec.spring.domain.community.CommentDTO;

import java.util.List;

public interface FeedService {
    List<CommentDTO> findAllOuterCommentByFeedId(Long feedId);

    List<ReplyDTO> findAllInnerCommentByParentId(Long parentId);
    List<FeedDTO> findAllCompFeed();

    List<FeedDTO> findAllCompFeedByNickname(String nickname);
    List<FeedDTO> findAllCompFeedByTag(String tag);

    List<FeedDTO> findAllCompFeedByAll(String keyword);

    int writeFeed(FeedDTO feed);

    int deleteFeed(Long feedId);

    void setTagListPerFeed(List<FeedDTO> list);
    void setShortContentPerFeed(List<FeedDTO> list);

    int updateFeed(FeedDTO feed);

    int trashFeed(Long feedId);

    FeedDTO findFeedById(Long id);

    List<FeedDTO> findAllCompFeedByUserId(Long userId);

    int trashFeedAllByUserId(Long userId);

    List<FeedDTO> findAllDelFeedByUserId(Long userId);

    int deleteFeedAllByUserId(Long userId);

    int restoreFeed(Long feedId);

    List<FeedDTO> findAllTempFeedByUserId(Long userId);
}
