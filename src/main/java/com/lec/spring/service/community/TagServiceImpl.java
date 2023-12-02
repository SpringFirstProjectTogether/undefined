package com.lec.spring.service.community;

import com.lec.spring.domain.community.FeedDTO;
import com.lec.spring.domain.community.TagDTO;
import com.lec.spring.repository.community.TagRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService{

    TagRepository tagRepository;

    public  TagServiceImpl(SqlSession sqlSession) {
        this.tagRepository = sqlSession.getMapper(TagRepository.class);
    }

    // feed 추가 시 tag add 하는 거
    @Override
    public int addTagForFeed(FeedDTO feed) {
        return 0;
    }




    // feed 수정 시 tag 수정 하는 거


}
