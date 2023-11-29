package com.lec.spring.repository.community;

import com.lec.spring.domain.community.FeedDTO;
import com.lec.spring.domain.community.InnerCommentDTO;
import com.lec.spring.domain.community.OuterCommentDTO;

import java.util.List;

public interface CommunityRepository {
    FeedDTO findFeedById(Long id);

    List<FeedDTO> findAllCompFeed();

    List<FeedDTO> findAllCompFeedByNickname(String nickname);

    List<FeedDTO> findAllCompFeedByTag(String tag);

    List<FeedDTO> findAllCompFeedByAll(String keyword);

    List<String> findTagsByFeedId(Long feedId);

    List<OuterCommentDTO> findOuterCommentsByFeedId(Long feedId);

    List<InnerCommentDTO> findInnerCommentsByParentId(Long outerCommentId);
}
