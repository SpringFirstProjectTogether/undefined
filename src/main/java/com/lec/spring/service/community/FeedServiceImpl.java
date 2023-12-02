package com.lec.spring.service.community;

import com.lec.spring.domain.UserDTO;
import com.lec.spring.domain.community.FeedDTO;
import com.lec.spring.domain.community.ReplyDTO;
import com.lec.spring.domain.community.CommentDTO;
import com.lec.spring.domain.community.TagDTO;
import com.lec.spring.repository.community.FeedRepository;
import com.lec.spring.repository.community.ReplyRepository;
import com.lec.spring.repository.community.CommentRepository;
import com.lec.spring.repository.community.TagRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class FeedServiceImpl implements FeedService {
    FeedRepository feedRepository;
    CommentRepository commentRepository;
    ReplyRepository replyRepository;
    TagRepository tagRepository;

    public FeedServiceImpl(SqlSession sqlSession) {
        this.feedRepository = sqlSession.getMapper(FeedRepository.class);
        this.commentRepository = sqlSession.getMapper(CommentRepository.class);
        this.replyRepository = sqlSession.getMapper(ReplyRepository.class);
        this.tagRepository = sqlSession.getMapper(TagRepository.class);

        System.out.println("FeedServiceImpl() 생성");
    }
    
    // feed 서비스는 feed와 관련된 동작들을 구현
    // 닉네임, 태그, 전체 별로 검색 기능
    // => findAllCompFeed, findAllCompFeedByNickname, findAllCompFeedByTag, findAllCompFeedByAll
    // => 다른 repository 와 같이 써야 할 듯
    // => 메소드마다 한 책임씩!! => 댓글 가져오는 메소드, 대댓글 가져오는 메소드

    // feed의 모든 댓글 가져오기
    public List<CommentDTO> findAllOuterCommentByFeedId(Long feedId) {
        var list = commentRepository.findCommentByFeedId(feedId);
        
        // 해당 댓글의 모든 대댓글 가져오기
        list.forEach(outer -> {
            outer.setReplyDTOList(findAllInnerCommentByParentId(outer.getCommentId()));
        });

        return list;
    }

    // 댓글의 모든 대댓글 가져오기
    @Override
    public List<ReplyDTO> findAllInnerCommentByParentId(Long parentId) {
        return replyRepository.findReplyByParentID(parentId);
    }
    
    // 완료된 모든 피드글 가져오기
    @Override
    public List<FeedDTO> findAllCompFeed() {
        var list = feedRepository.findAllCompFeed();

        if(list != null) {
            // 해당 피드의 모든 댓글 가져오기
            list.forEach(feed -> {
                feed.setComments(findAllOuterCommentByFeedId(feed.getFeedId()));
            });

            setShortContentPerFeed(list);
            setTagListPerFeed(list);
        }

        return list;
    }

    // 해당 Nickname을 가진 유저가 작성한 모든 피드글 가져오기
    // 추후에 완료글 리스트에서도 사용
    @Override
    public List<FeedDTO> findAllCompFeedByNickname(String nickname) {
        var list = feedRepository.findAllCompFeedByNickname(nickname);

        if(list != null) {
            // 해당 피드의 모든 댓글 가져오기
            list.forEach(feed -> {
                feed.setComments(findAllOuterCommentByFeedId(feed.getFeedId()));
            });

            setShortContentPerFeed(list);
            setTagListPerFeed(list);
        }

        return list;
    }

    // 해당 태그를 가지는 모든 피드 글 가져오기
    @Override
    public List<FeedDTO> findAllCompFeedByTag(String tag) {
        // 해당 tag를 가지고 있는 feed_id 리스트 호출
        Set<Long> feedIdList = tagRepository.feedIdListByTag(tag);
        
        // feed_id list 에 포함되어 있는 feed 불러오기
        var list = feedRepository.findAllCompFeedByTag(feedIdList);

        if(list != null) {
            // 해당 피드의 모든 댓글 가져오기
            list.forEach(feed -> {
                feed.setComments(findAllOuterCommentByFeedId(feed.getFeedId()));
            });

            setShortContentPerFeed(list);
            setTagListPerFeed(list);
        }

        return list;
    }

    // 닉네임, 태그 찾기 기능이 합쳐진 전체 기능을 위한 메소드
    @Override
    public List<FeedDTO> findAllCompFeedByAll(String keyword) {
        // 해당 키워드를 태그로 가지는 feed_id 불러오기
        Set<Long> feedIdList = tagRepository.feedIdListByTag(keyword);

        // 해당 키워드를 닉네임으로 가지는 feed_id 추가
        if(feedIdList != null) {
            feedIdList.addAll(feedRepository.feedIdListByNickname(keyword));
        } else {
            feedIdList = feedRepository.feedIdListByNickname(keyword);
        }

        var list = feedRepository.findAllCompFeedByAll(feedIdList);

        if(list != null) {
            // 해당 피드의 모든 댓글 가져오기
            list.forEach(feed -> {
                feed.setComments(findAllOuterCommentByFeedId(feed.getFeedId()));
            });

            setShortContentPerFeed(list);
            setTagListPerFeed(list);
        }

        return list;
    }

    // 해당 피드의 태그 목록을 저장
    @Override
    public void setTagListPerFeed(List<FeedDTO> list) {
        for(var feed : list) {
            // 해당 피드의 태그 목록을 스트링으로 저장
            // 이거 tag repository로 바꾸자
            List<String> tagList = feedRepository.findTagsByFeedId(feed.getFeedId());
            StringBuffer sb = new StringBuffer();
            tagList.forEach(tag -> sb.append("#").append(tag).append(" "));
            feed.setTagList(sb.toString());
//            System.out.println("tag : " + feed.getTagList());
        }
    }
    
    // 피드의 내용이 넘칠 때, 축소본
    @Override
    public void setShortContentPerFeed(List<FeedDTO> list) {
        for(var feed : list) {
            // 피드의 내용이 100자를 넘기게 된다면 축소분을 초기화시키자.
            if(feed.getFeedContent().length() > 100) {
                feed.setShortContent(feed.getFeedContent().substring(0, 100));
            }

        }
    }


    // 특정 id의 피드 글 불러오는 메소드
    // update 시 기존의 내용을 보여주기 위함
    @Override
    public FeedDTO findFeedById(Long id) {
        List<FeedDTO> feed = List.of(feedRepository.findFeedById(id));
        setTagListPerFeed(feed);

        return feed.get(0);
    }

    @Override
    public List<FeedDTO> findAllCompFeedByUserId(Long userId) {
        var list =  feedRepository.findAllCompFeedByUserId(userId);

        if(list != null) {
            setShortContentPerFeed(list);
            setTagListPerFeed(list);
        }

        return list;
    }

    @Override
    public List<FeedDTO> findAllDelFeedByUserId(Long userId) {
        var list =  feedRepository.findAllDelFeedByUserId(userId);

        if(list != null) {
            setShortContentPerFeed(list);
            setTagListPerFeed(list);
        }

        return list;
    }

    @Override
    public List<FeedDTO> findAllTempFeedByUserId(Long userId) {
        var list =  feedRepository.findAllTempFeedByUserId(userId);

        if(list != null) {
            setShortContentPerFeed(list);
            setTagListPerFeed(list);
        }

        return list;
    }


    // feed 추가/삭제/수정

    // feed 추가 -> comp, temp 구분 / del => comp 를 del 로 바꾸는 repository 메소드로
    // feed 추가하기 위해 뭐가 필요?
    // => feed 테이블에 들어갈 내용 
    // User : 이건 흠...일단 나중에 security 구현하면서 해보자 : 임의로 feed의 user 세팅 필요
    // 첨부 파일에 관해 다루는 메소드도 필요
    @Override
    public int writeFeed(FeedDTO feed) {

        // add 하기 위해서 필요한 작업
        // user 초기화
        // tag_id 생성 작업, 해당 태그와 작성된 feed_id를 연결하는 작업
        // 파일 관련 작업
        
        // 임의로 하나 생성
        UserDTO user = UserDTO.builder()
                .userId(1L)
                .loginId("id1")
                .password("password_1")
                .nickname("User1")
                .build();
        feed.setUser(user);

        // feed를 추가
        int result = feedRepository.saveFeed(feed);

        // Tag 추가 코드는 분리해도 될듯??
        if(result == 1) {
            result = addTagForFeed(feed);
        }

        // 첨부 파일 추가
        // TODO


        return result;
    }

    private int addTagForFeed(FeedDTO feed) {

        Long feedId = feed.getFeedId();
        int result = 1;

        // tag list 만들기
        List<String> taglist = List.of(feed.getTagList().trim().split(","));

        for(var tag : taglist) {
            TagDTO isExist = tagRepository.findTagByName(tag.trim());
            Long tagId = 0L;
            if(isExist == null) {
                // 새로운 tag 일 시, 저장하고 pk 값 받아오기
                TagDTO newTagDTO = new TagDTO();
                newTagDTO.setTagName(tag.trim());
                int success = tagRepository.addTag(newTagDTO);

                if(success == 1) tagId = newTagDTO.getTagId();

            } else {
                tagId = isExist.getTagId();
            }

            result = tagRepository.addTagAndFeed(feedId, tagId);
            if(result == 0) return result;
        }

        return result;
    }
    
    
    // 피드 삭제
    // 아마 delete CASCADE라 해당 피드를 참조하는 다른 테이블의 데이터도 삭제
    // 요놈은 진짜 삭제하는 놈임
    // 임시저장, 휴지통에서 삭제할 때 쓰느 메소드
    
    // 하나만 삭제
    @Override
    public int deleteFeed(Long feedId){
        int result = 0;
        FeedDTO feed = feedRepository.findFeedById(feedId);

        if(feed != null) {
            result = feedRepository.deleteFeedById(feedId);
        }

        return result;
    }

    // 모두 삭제
    @Override
    public int deleteFeedAllByUserId(Long userId) {
        return feedRepository.deleteFeedAllByUesrId(userId);
    }

    // 휴지통 보내기
    // 해당 피드의 state 만 comp -> del 로
    // 임시 저장글은 삭제 시 db에서 완전 삭제됨 (휴지통X)
    
    // 하나만 삭제
    @Override
    public int trashFeed(Long feedId) {
        int result = 0;
        FeedDTO feed = feedRepository.findFeedById(feedId);

        if(feed != null) {
            feed.setFeedState("del");
            result = feedRepository.updateFeed(feed);
        }
        return result;
    }

    // 모두 삭제
    @Override
    public int trashFeedAllByUserId(Long userId) {

        List<FeedDTO> feedList = feedRepository.findAllCompFeedByUserId(userId);

        for(var feed : feedList) {
            feed.setFeedState("del");
            if(feedRepository.updateFeed(feed) == 0) return 0;
        }

        return 1;
    }


    // 피드 복구
    // 해당 피드의 state만 del -> comp로
    @Override
    public int restoreFeed(Long feedId) {
        int result = 0;
        FeedDTO feed = feedRepository.findFeedById(feedId);

        if(feed != null) {
            feed.setFeedState("comp");
            result = feedRepository.updateFeed(feed);
        }

        return result;
    }


    // 피드 수정
    @Override
    public int updateFeed(FeedDTO feed) {
        int result = feedRepository.updateFeed(feed);

        // 태그 수정
        if(result == 1) {
            result = editTagForFeed(feed);
        }
        
        // 새로운 첨부 파일 추가
        
        // 삭제할 첨부파일 삭제
        
        return result;
    }

    private int editTagForFeed(FeedDTO feed) {

        Long feedId = feed.getFeedId();
        int result = 1;

        // tag list 만들기
        List<String> taglist = List.of(feed.getTagList().trim().split(","));

        // 해당 태그를 기존의 feed 가 가지고 있는지 검사 => origin_tag_id_list , new_tag_id_list
        List<Long> originTagList = tagRepository.findTagIdByFeedIDd(feedId);
        List<Long> newTagList = new ArrayList<>();
        for(var tag : taglist) {
            // tag 의 id 값 가져오기
            TagDTO isExist = tagRepository.findTagByName(tag.trim());
            Long tagId = 0L;
            if(isExist == null) {
                // 새로운 tag 일 시, 저장하고 pk 값 받아오기
                TagDTO newTagDTO = new TagDTO();
                newTagDTO.setTagName(tag.trim());
                int success = tagRepository.addTag(newTagDTO);

                if(success == 1) tagId = newTagDTO.getTagId();

            } else {
                tagId = isExist.getTagId();
            }

            newTagList.add(tagId);
        }

        // 비교 들가자
        // origin 태그의 new 태그가 없으면? 추가
        for(var id : newTagList) {
            if(!originTagList.contains(id)){
                result = tagRepository.addTagAndFeed(feedId, id);
                if(result == 0) return 0;
            }
        }

        // new 태그의 origin 태그가 없으면? 삭제
        for(var id : originTagList) {
            if(!newTagList.contains(id)){
                result = tagRepository.deleteTagAndFeed(feedId, id);
                if(result == 0) return 0;
            }
        }

        return result;
    }

}
