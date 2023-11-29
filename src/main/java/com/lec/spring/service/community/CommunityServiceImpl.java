package com.lec.spring.service.community;

import com.lec.spring.domain.community.FeedDTO;
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
        return setTagListPerFeed(communityRepository.findAllCompFeed());
    }

    @Override
    public List<FeedDTO> findAllCompFeedByNickname(String nickname) {
        return setTagListPerFeed(communityRepository.findAllCompFeedByNickname(nickname));
    }

    @Override
    public List<FeedDTO> findAllCompFeedByTag(String tag) {
        return setTagListPerFeed(communityRepository.findAllCompFeedByTag(tag));
    }

    @Override
    public List<FeedDTO> findAllCompFeedByAll(String keyword) {
        return setTagListPerFeed(communityRepository.findAllCompFeedByAll(keyword));
    }

    public List<FeedDTO> setTagListPerFeed(List<FeedDTO> list) {
        for(var feed : list) {
            List<String> tagList = communityRepository.findTagsByFeedId(feed.getFeedId());
            StringBuffer sb = new StringBuffer();
            tagList.forEach(tag -> sb.append("#").append(tag).append(" "));
            feed.setTagList(sb.toString());
        }
        return list;
    }

    @Override
    public List<String> findTagsByFeedId(Long feedId) {
        return communityRepository.findTagsByFeedId(feedId);
    }


}
