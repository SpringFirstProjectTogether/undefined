package com.lec.spring.service.community;

import com.lec.spring.domain.UserDTO;
import com.lec.spring.domain.community.ReplyDTO;
import com.lec.spring.repository.community.ReplyRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService {
    ReplyRepository replyRepository;

    public ReplyServiceImpl(SqlSession sqlSession) {
        this.replyRepository = sqlSession.getMapper(ReplyRepository.class);
    }

    @Override
    public int addReplyByParentId(Long parentId, Long feedId, String content) {
        // 임의로 하나 생성
        UserDTO user = UserDTO.builder()
                .userId(1L)
                .loginId("id1")
                .password("password_1")
                .nickname("User1")
                .build();

        ReplyDTO replyDTO = ReplyDTO.builder()
                .replyContent(content)
                .commentId(parentId)
                .replyFeedId(feedId)
                .user(user)
                .build();


        return replyRepository.addReplyComment(replyDTO);
    }

    @Override
    public int deleteReplyById(Long replyId) {
        return replyRepository.deleteReplyCommentById(replyId);
    }


}
