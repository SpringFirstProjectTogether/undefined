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
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.lec.spring.util.U;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class FeedServiceImpl implements FeedService {

    @Value("${app.pagination.page_rows}")
    private int PAGE_ROWS;

    @Value("${app.pagination.write_pages}")
    private int WRITE_PAGES;

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

    // feed의 모든 댓글 가져오기
    public List<CommentDTO> findCommentsByFeedId(Long feedId) {
        var list = commentRepository.findCommentsByFeedId(feedId);

        // 해당 댓글의 모든 대댓글 가져오기
        list.forEach(outer -> {
            outer.setReplyDTOList(findRepliesByParentId(outer.getCommentId()));
        });

        return list;
    }

    // 댓글의 모든 대댓글 가져오기
    public List<ReplyDTO> findRepliesByParentId(Long parentId) {
        return replyRepository.findReplyByParentID(parentId);
    }


    // 해당 피드의 태그 목록을 저장
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
    public void setShortContentPerFeed(List<FeedDTO> list) {
        for(var feed : list) {
            // 피드의 내용이 100자를 넘기게 된다면 축소분을 초기화시키자.
            if(feed.getFeedContent().length() > 100) {
                feed.setShortContent(feed.getFeedContent().substring(0, 100));
            }

        }
    }

    @Override
    public List<FeedDTO> list(Integer page, Model model) {
        // 현재 페이지 parameter
        if(page == null) page = 1;  // 디폴트는 1page
        if(page < 1) page = 1;

        // 페이징
        // writePages: 한 [페이징] 당 몇개의 페이지가 표시되나
        // pageRows: 한 '페이지'에 몇개의 글을 리스트 할것인가?
        HttpSession session = U.getSession();
        Integer writePages = (Integer)session.getAttribute("writePages");
        if(writePages == null) writePages = WRITE_PAGES;  // 만약 session 에 없으면 기본값으로 동작
        Integer pageRows = (Integer)session.getAttribute("pageRows");
        if(pageRows == null) pageRows = PAGE_ROWS;  // 만약 session 에 없으면 기본값으로 동작

        // 현재 페이지 번호 -> session 에 저장
        session.setAttribute("page", page);

        long cnt = feedRepository.countAll();   // 글 목록 전체의 개수
        int totalPage = (int)Math.ceil(cnt / (double)pageRows);   // 총 몇 '페이지' ?

        // [페이징] 에 표시할 '시작페이지' 와 '마지막페이지'
        int startPage = 0;
        int endPage = 0;

        // 해당 페이지의 글 목록
        List<FeedDTO> list = null;

        if(cnt > 0){  // 데이터가 최소 1개 이상 있는 경우만 페이징
            //  page 값 보정
            if(page > totalPage) page = totalPage;

            // 몇번째 데이터부터 fromRow
            int fromRow = (page - 1) * pageRows;

            // [페이징] 에 표시할 '시작페이지' 와 '마지막페이지' 계산
            startPage = (((page - 1) / writePages) * writePages) + 1;
            endPage = startPage + writePages - 1;
            if (endPage >= totalPage) endPage = totalPage;

            // 해당페이지의 글 목록 읽어오기
            LocalDateTime start = LocalDateTime.now();
            list = feedRepository.findAllCompFeedFromRow(fromRow, pageRows);
            if(list != null) {
                // 해당 피드의 모든 댓글 가져오기
                list.forEach(feed -> {
                    feed.setComments(findCommentsByFeedId(feed.getFeedId()));
                });

                setShortContentPerFeed(list);
                setTagListPerFeed(list);
            }
            LocalDateTime end = LocalDateTime.now();
            model.addAttribute("list", list);

            Duration diff = Duration.between(start.toLocalTime(), end.toLocalTime());
            model.addAttribute("searchTime", diff.toMillis() / 1000.00);
        } else {
            page = 0;
        }

        model.addAttribute("option", "all");
        model.addAttribute("totalCnt", cnt);  // 전체 글 개수
        model.addAttribute("page", page); // 현재 페이지
        model.addAttribute("totalPage", totalPage);  // 총 '페이지' 수
        model.addAttribute("pageRows", pageRows);  // 한 '페이지' 에 표시할 글 개수

        // [페이징]
        model.addAttribute("url", U.getRequest().getRequestURI());  // 목록 url
        model.addAttribute("writePages", writePages); // [페이징] 에 표시할 숫자 개수
        model.addAttribute("startPage", startPage);  // [페이징] 에 표시할 시작 페이지
        model.addAttribute("endPage", endPage);   // [페이징] 에 표시할 마지막 페이지

        return list;
    }

    @Override
    public void listByOption(String option, String keyword, Integer page, Model model) {
        HttpSession session = U.getSession();
        if(option == null) option = (String) session.getAttribute("searchOption");
        else session.setAttribute("searchOption", option);

        if(keyword == null) keyword = (String) session.getAttribute("keyword");
        else session.setAttribute("keyword", keyword);

        if(!keyword.isEmpty()) {
            switch (option) {
                case "nick" -> listByNickname(keyword, page, model);
                case "tag" -> listByTag(keyword, page, model);
                default -> listByAll(keyword, page, model);
            }
        } else {
            session.setAttribute("searchOption", "all");
            list(page, model);
        }
    }


    // 해당 Nickname을 가진 유저가 작성한 모든 피드글 가져오기
    // 추후에 완료글 리스트에서도 사용
    @Override
    public List<FeedDTO> listByNickname(String nickname, Integer page, Model model) {
        // 현재 페이지 parameter
        if(page == null) page = 1;  // 디폴트는 1page
        if(page < 1) page = 1;

        HttpSession session = U.getSession();
        Integer writePages = (Integer)session.getAttribute("writePages");
        if(writePages == null) writePages = WRITE_PAGES;  // 만약 session 에 없으면 기본값으로 동작
        Integer pageRows = (Integer)session.getAttribute("pageRows");
        if(pageRows == null) pageRows = PAGE_ROWS;  // 만약 session 에 없으면 기본값으로 동작

        // 현재 페이지 번호 -> session 에 저장
        session.setAttribute("page", page);

        long cnt = feedRepository.countAllByNickname(nickname);   // 글 목록 전체의 개수
        int totalPage = (int)Math.ceil(cnt / (double)pageRows);   // 총 몇 '페이지' ?

        int startPage = 0;
        int endPage = 0;

        List<FeedDTO> list = null;

        if(cnt > 0){
            if(page > totalPage) page = totalPage;
            int fromRow = (page - 1) * pageRows;

            startPage = (((page - 1) / writePages) * writePages) + 1;
            endPage = startPage + writePages - 1;
            if (endPage >= totalPage) endPage = totalPage;

            LocalDateTime start = LocalDateTime.now();
            list = feedRepository.listByNicknameFromRow(nickname, fromRow, pageRows);
            if(list != null) {
                list.forEach(feed -> {
                    feed.setComments(findCommentsByFeedId(feed.getFeedId()));
                });

                setShortContentPerFeed(list);
                setTagListPerFeed(list);
            }
            LocalDateTime end = LocalDateTime.now();
            model.addAttribute("list", list);

            Duration diff = Duration.between(start.toLocalTime(), end.toLocalTime());
            model.addAttribute("searchTime", diff.toMillis() / 1000.00);
        } else {
            page = 0;
        }

        model.addAttribute("option", "nick");
        model.addAttribute("totalCnt", cnt);  // 전체 글 개수
        model.addAttribute("page", page); // 현재 페이지
        model.addAttribute("totalPage", totalPage);  // 총 '페이지' 수
        model.addAttribute("pageRows", pageRows);  // 한 '페이지' 에 표시할 글 개수

        // [페이징]
        model.addAttribute("url", U.getRequest().getRequestURI());  // 목록 url
        model.addAttribute("writePages", writePages); // [페이징] 에 표시할 숫자 개수
        model.addAttribute("startPage", startPage);  // [페이징] 에 표시할 시작 페이지
        model.addAttribute("endPage", endPage);   // [페이징] 에 표시할 마지막 페이지

        return list;
    }

    @Override
    public List<FeedDTO> listByTag(String tag, Integer page, Model model) {
        // 현재 페이지 parameter
        if(page == null) page = 1;  // 디폴트는 1page
        if(page < 1) page = 1;

        HttpSession session = U.getSession();
        Integer writePages = (Integer)session.getAttribute("writePages");
        if(writePages == null) writePages = WRITE_PAGES;  // 만약 session 에 없으면 기본값으로 동작
        Integer pageRows = (Integer)session.getAttribute("pageRows");
        if(pageRows == null) pageRows = PAGE_ROWS;  // 만약 session 에 없으면 기본값으로 동작

        // 현재 페이지 번호 -> session 에 저장
        session.setAttribute("page", page);

        Set<Long> feedIdList = tagRepository.feedIdListByTag(tag);
        long cnt = feedRepository.countAllByTag(feedIdList);   // 글 목록 전체의 개수
        int totalPage = (int)Math.ceil(cnt / (double)pageRows);   // 총 몇 '페이지' ?

        int startPage = 0;
        int endPage = 0;

        List<FeedDTO> list = null;

        if(cnt > 0){
            if(page > totalPage) page = totalPage;
            int fromRow = (page - 1) * pageRows;

            startPage = (((page - 1) / writePages) * writePages) + 1;
            endPage = startPage + writePages - 1;
            if (endPage >= totalPage) endPage = totalPage;

            LocalDateTime start = LocalDateTime.now();
            list = feedRepository.listByTagFromRow(feedIdList, fromRow, pageRows);
            if(list != null) {
                list.forEach(feed -> {
                    feed.setComments(findCommentsByFeedId(feed.getFeedId()));
                });

                setShortContentPerFeed(list);
                setTagListPerFeed(list);
            }
            LocalDateTime end = LocalDateTime.now();
            model.addAttribute("list", list);

            Duration diff = Duration.between(start.toLocalTime(), end.toLocalTime());
            model.addAttribute("searchTime", diff.toMillis() / 1000.00);
        } else {
            page = 0;
        }

        model.addAttribute("option", "tag");
        model.addAttribute("totalCnt", cnt);  // 전체 글 개수
        model.addAttribute("page", page); // 현재 페이지
        model.addAttribute("totalPage", totalPage);  // 총 '페이지' 수
        model.addAttribute("pageRows", pageRows);  // 한 '페이지' 에 표시할 글 개수

        // [페이징]
        model.addAttribute("url", U.getRequest().getRequestURI());  // 목록 url
        model.addAttribute("writePages", writePages); // [페이징] 에 표시할 숫자 개수
        model.addAttribute("startPage", startPage);  // [페이징] 에 표시할 시작 페이지
        model.addAttribute("endPage", endPage);   // [페이징] 에 표시할 마지막 페이지

        return list;
    }

    @Override
    public List<FeedDTO> listByAll(String keyword, Integer page, Model model) {
        // 현재 페이지 parameter
        if(page == null) page = 1;  // 디폴트는 1page
        if(page < 1) page = 1;

        HttpSession session = U.getSession();
        Integer writePages = (Integer)session.getAttribute("writePages");
        if(writePages == null) writePages = WRITE_PAGES;  // 만약 session 에 없으면 기본값으로 동작
        Integer pageRows = (Integer)session.getAttribute("pageRows");
        if(pageRows == null) pageRows = PAGE_ROWS;  // 만약 session 에 없으면 기본값으로 동작

        // 현재 페이지 번호 -> session 에 저장
        session.setAttribute("page", page);

        Set<Long> feedIdList = tagRepository.feedIdListByTag(keyword);
        // 해당 키워드를 닉네임으로 가지는 feed_id 추가
        if(feedIdList != null) {
            feedIdList.addAll(feedRepository.feedIdListByNickname(keyword));
        } else {
            feedIdList = feedRepository.feedIdListByNickname(keyword);
        }


        long cnt = feedRepository.countAllByTag(feedIdList);   // 글 목록 전체의 개수
        int totalPage = (int)Math.ceil(cnt / (double)pageRows);   // 총 몇 '페이지' ?

        int startPage = 0;
        int endPage = 0;

        List<FeedDTO> list = null;

        if(cnt > 0){
            if(page > totalPage) page = totalPage;
            int fromRow = (page - 1) * pageRows;

            startPage = (((page - 1) / writePages) * writePages) + 1;
            endPage = startPage + writePages - 1;
            if (endPage >= totalPage) endPage = totalPage;

            LocalDateTime start = LocalDateTime.now();
            list = feedRepository.listByAllFromRow(feedIdList, fromRow, pageRows);
            if(list != null) {
                list.forEach(feed -> {
                    feed.setComments(findCommentsByFeedId(feed.getFeedId()));
                });

                setShortContentPerFeed(list);
                setTagListPerFeed(list);
            }
            LocalDateTime end = LocalDateTime.now();
            model.addAttribute("list", list);

            Duration diff = Duration.between(start.toLocalTime(), end.toLocalTime());
            model.addAttribute("searchTime", diff.toMillis() / 1000.00);
        } else {
            page = 0;
        }

        model.addAttribute("option", "all");
        model.addAttribute("totalCnt", cnt);  // 전체 글 개수
        model.addAttribute("page", page); // 현재 페이지
        model.addAttribute("totalPage", totalPage);  // 총 '페이지' 수
        model.addAttribute("pageRows", pageRows);  // 한 '페이지' 에 표시할 글 개수

        // [페이징]
        model.addAttribute("url", U.getRequest().getRequestURI());  // 목록 url
        model.addAttribute("writePages", writePages); // [페이징] 에 표시할 숫자 개수
        model.addAttribute("startPage", startPage);  // [페이징] 에 표시할 시작 페이지
        model.addAttribute("endPage", endPage);   // [페이징] 에 표시할 마지막 페이지

        return list;
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
    public List<FeedDTO> listByUserId(Long userId, Integer page, Model model, String state) {

        if(page == null) page = 1;  // 디폴트는 1page
        if(page < 1) page = 1;

        HttpSession session = U.getSession();
        Integer writePages = (Integer)session.getAttribute("writePages");
        if(writePages == null) writePages = WRITE_PAGES;  // 만약 session 에 없으면 기본값으로 동작
        Integer pageRows = (Integer)session.getAttribute("pageRows");
        if(pageRows == null) pageRows = PAGE_ROWS;  // 만약 session 에 없으면 기본값으로 동작

        // 현재 페이지 번호 -> session 에 저장
        session.setAttribute("page", page);

        long cnt = feedRepository.countFeedByUserId(userId, state);   // 글 목록 전체의 개수
        int totalPage = (int)Math.ceil(cnt / (double)pageRows);   // 총 몇 '페이지' ?

        int startPage = 0;
        int endPage = 0;

        List<FeedDTO> list = null;

        if(cnt > 0){
            if(page > totalPage) page = totalPage;
            int fromRow = (page - 1) * pageRows;

            startPage = (((page - 1) / writePages) * writePages) + 1;
            endPage = startPage + writePages - 1;
            if (endPage >= totalPage) endPage = totalPage;

            LocalDateTime start = LocalDateTime.now();
            list = feedRepository.myFeedFromRow(userId, fromRow, pageRows, state);
            if(list != null) {
                list.forEach(feed -> {
                    feed.setComments(findCommentsByFeedId(feed.getFeedId()));
                });

                setShortContentPerFeed(list);
                setTagListPerFeed(list);
            }
            LocalDateTime end = LocalDateTime.now();
            model.addAttribute("list", list);

            Duration diff = Duration.between(start.toLocalTime(), end.toLocalTime());
            model.addAttribute("searchTime", diff.toMillis() / 1000.00);
        } else {
            page = 0;
        }

        model.addAttribute("totalCnt", cnt);  // 전체 글 개수
        model.addAttribute("page", page); // 현재 페이지
        model.addAttribute("totalPage", totalPage);  // 총 '페이지' 수
        model.addAttribute("pageRows", pageRows);  // 한 '페이지' 에 표시할 글 개수

        // [페이징]
        model.addAttribute("url", U.getRequest().getRequestURI());  // 목록 url
        model.addAttribute("writePages", writePages); // [페이징] 에 표시할 숫자 개수
        model.addAttribute("startPage", startPage);  // [페이징] 에 표시할 시작 페이지
        model.addAttribute("endPage", endPage);   // [페이징] 에 표시할 마지막 페이지

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

        // 임시 저장 글이고 내용이 존재하지 않는 경우에는 빈 문자열을 추가
        if(feed.getFeedState().equals("temp")) {
            if(feed.getFeedContent().isEmpty()) {
                feed.setFeedContent("");
            }
        }

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
        if(!feed.getTagList().isEmpty()) {
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
        } else {
            feed.setTagList("");
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
    public int deleteFeedAllByUserId(Long userId, String state) {
        return feedRepository.deleteFeedAllByUserId(userId, state);
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
        System.out.println("update feed : " + feed.getFeedId());
        System.out.println("update feed tag: " + feed.getTagList());
        System.out.println("update feedDTO : " + feed);
        int result = feedRepository.updateFeed(feed);

        if(feed.getFeedState().equals("temp")) {
            if(feed.getFeedContent().isEmpty()) {
                feed.setFeedContent("");
            }
        }

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

        if(!feed.getTagList().isEmpty()) {
            // tag list 만들기
            List<String> taglist = List.of(feed.getTagList().trim().split(","));

            // 해당 태그를 기존의 feed 가 가지고 있는지 검사 => origin_tag_id_list , new_tag_id_list
            List<Long> originTagList = tagRepository.findTagIdByFeedIDd(feedId);
            List<Long> newTagList = new ArrayList<>();
            for (var tag : taglist) {
                // tag 의 id 값 가져오기
                TagDTO isExist = tagRepository.findTagByName(tag.trim());
                Long tagId = 0L;
                if (isExist == null) {
                    // 새로운 tag 일 시, 저장하고 pk 값 받아오기
                    TagDTO newTagDTO = new TagDTO();
                    newTagDTO.setTagName(tag.trim());
                    int success = tagRepository.addTag(newTagDTO);

                    if (success == 1) tagId = newTagDTO.getTagId();

                } else {
                    tagId = isExist.getTagId();
                }

                newTagList.add(tagId);
            }

            // 비교 들가자
            // origin 태그의 new 태그가 없으면? 추가
            for (var id : newTagList) {
                if (!originTagList.contains(id)) {
                    result = tagRepository.addTagAndFeed(feedId, id);
                    if (result == 0) return 0;
                }
            }

            // new 태그의 origin 태그가 없으면? 삭제
            for (var id : originTagList) {
                if (!newTagList.contains(id)) {
                    result = tagRepository.deleteTagAndFeed(feedId, id);
                    if (result == 0) return 0;
                }
            }
        } else {
            feed.setTagList("");
        }

        return result;
    }

}
