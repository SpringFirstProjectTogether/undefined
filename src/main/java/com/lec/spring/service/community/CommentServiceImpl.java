package com.lec.spring.service.community;

import com.lec.spring.domain.UserDTO;
import com.lec.spring.domain.community.CommentDTO;
import com.lec.spring.repository.community.CommentRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    CommentRepository commentRepository;

    public CommentServiceImpl(SqlSession sqlSession) {
        this.commentRepository = sqlSession.getMapper(CommentRepository.class);
    }


    @Override
    public int addCommentByFeedId(Long feedId, String content) {
        // 임의로 하나 생성
        UserDTO user = UserDTO.builder()
                .userId(1L)
                .loginId("id1")
                .password("password_1")
                .nickname("User1")
                .build();

        CommentDTO commentDTO = CommentDTO.builder()
                .commentFeedId(feedId)
                .commentContent(content)
                .user(user)
                .build();

        return commentRepository.addComment(commentDTO);
    }

    @Override
    public int deleteCommentById(Long commentId) {
        return commentRepository.deleteComment(commentId);
    }
}
