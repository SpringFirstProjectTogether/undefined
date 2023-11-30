package com.lec.spring.service.community;

import com.lec.spring.domain.community.FeedDTO;
import com.lec.spring.domain.community.OuterCommentDTO;
import com.lec.spring.repository.community.CommunityRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityServiceImpl implements CommunityService{
    CommunityRepository communityRepository;

    public CommunityServiceImpl(SqlSession sqlSession) {
        communityRepository = sqlSession.getMapper(CommunityRepository.class);
    }

    @Override
    public List<FeedDTO> findAllCompFeed() {
        return setOtherInfoPerFeed(communityRepository.findAllCompFeed());
    }

    @Override
    public List<FeedDTO> findAllCompFeedByNickname(String nickname) {
        return setOtherInfoPerFeed(communityRepository.findAllCompFeedByNickname(nickname));
    }

    @Override
    public List<FeedDTO> findAllCompFeedByTag(String tag) {
        return setOtherInfoPerFeed(communityRepository.findAllCompFeedByTag(tag));
    }

    @Override
    public List<FeedDTO> findAllCompFeedByAll(String keyword) {
        return setOtherInfoPerFeed(communityRepository.findAllCompFeedByAll(keyword));
    }

    // 해당 피드의 태그 목록을 저장
    // 그리고 댓글 목록도 초기화해주는 작업을 해주자
    // 피드의 내용이 넘칠 때, 축소본
    public List<FeedDTO> setOtherInfoPerFeed(List<FeedDTO> list) {
        for(var feed : list) {
            // 해당 피드의 태그 목록을 스트링으로 저장
            List<String> tagList = communityRepository.findTagsByFeedId(feed.getFeedId());
            StringBuffer sb = new StringBuffer();
            tagList.forEach(tag -> sb.append("#").append(tag).append(" "));
            feed.setTagList(sb.toString());

            // 해당 피드의 댓글 목록 초기화해주어야 한다.
            // 먼저 부모 댓글들을 불러와보자.
            List<OuterCommentDTO> outerComments = communityRepository.findOuterCommentsByFeedId(feed.getFeedId());
            feed.setComments(outerComments);

            // 부모 댓글의 자식 댓글들을 불러오자
            feed.getComments().forEach(outerCommentDTO -> {
                outerCommentDTO.setInnerCommentDTOList(communityRepository.findInnerCommentsByParentId(outerCommentDTO.getOuterCommentId()));
            });

            // 피드의 내용이 100자를 넘기게 된다면 축소분을 초기화시키자.
            if(feed.getFeedContent().length() > 100) {
                feed.setShortContent(feed.getFeedContent().substring(0, 100));
            }

        }
        return list;
    }

    @Override
    public List<String> findTagsByFeedId(Long feedId) {
        return communityRepository.findTagsByFeedId(feedId);
    }


}
