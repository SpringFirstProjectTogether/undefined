package com.lec.spring.repository.community;

import com.lec.spring.domain.UserDTO;
import com.lec.spring.domain.community.FeedDTO;
import com.lec.spring.service.community.FeedService;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

@SpringBootTest
class FeedRepositoryTest {

    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private FeedService feedService;

    @Test
    public void test01() {
        FeedRepository feedRepository = sqlSession.getMapper(FeedRepository.class);
        TagRepository tagRepository = sqlSession.getMapper(TagRepository.class);

        Set<Long> feedlist = tagRepository.feedIdListByTag("security");
        System.out.println("feed_id list : " + feedlist);
        List<FeedDTO> list = feedRepository.findAllCompFeedByTag(feedlist);

        list.forEach(System.out::println);
    }

    @Test
    public void test02() {
        FeedRepository feedRepository = sqlSession.getMapper(FeedRepository.class);

//        List<FeedDTO> list = feedRepository.findAllCompFeedByAll("spring");
//        list.forEach(System.out::println);
    }

    @Test
    public void test03() {
        FeedRepository feedRepository = sqlSession.getMapper(FeedRepository.class);

        List<String> list = feedRepository.findTagsByFeedId(1L);
        list.forEach(System.out::println);
    }

    @Test
    public void test04() {
        FeedRepository feedRepository = sqlSession.getMapper(FeedRepository.class);

        // 임의로 하나 생성
        UserDTO user = UserDTO.builder()
                .userId(1L)
                .loginId("id1")
                .password("password_1")
                .nickname("User1")
                .build();

        FeedDTO feed = FeedDTO.builder()
                .feedTitle("test title1")
                .feedContent("test content12")
                .feedState("comp")
                .user(user)
                .build();

        int result = feedRepository.saveFeed(feed);
        System.out.println(result);

    }

}