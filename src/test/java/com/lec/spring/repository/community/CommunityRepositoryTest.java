package com.lec.spring.repository.community;

import com.lec.spring.domain.community.FeedDTO;
import com.lec.spring.service.community.CommunityService;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommunityRepositoryTest {

    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private CommunityService communityService;

    @Test
    public void test01() {
        CommunityRepository communityRepository = sqlSession.getMapper(CommunityRepository.class);

        List<FeedDTO> list = communityRepository.findAllCompFeedByTag("spring");
        list.forEach(System.out::println);
    }

    @Test
    public void test02() {
        CommunityRepository communityRepository = sqlSession.getMapper(CommunityRepository.class);

        List<FeedDTO> list = communityRepository.findAllCompFeedByAll("spring");
        list.forEach(System.out::println);
    }

    @Test
    public void test03() {
        CommunityRepository communityRepository = sqlSession.getMapper(CommunityRepository.class);

        List<String> list = communityRepository.findTagsByFeedId(1L);
        list.forEach(System.out::println);
    }

}