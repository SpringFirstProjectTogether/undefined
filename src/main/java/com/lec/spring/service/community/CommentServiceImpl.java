package com.lec.spring.service.community;

import com.lec.spring.domain.UserDTO;
import com.lec.spring.domain.community.CommentDTO;
import com.lec.spring.domain.community.QryCommentList;
import com.lec.spring.domain.community.QryResult;
import com.lec.spring.repository.community.CommentRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    CommentRepository commentRepository;

    public CommentServiceImpl(SqlSession sqlSession) {
        this.commentRepository = sqlSession.getMapper(CommentRepository.class);
    }


    @Override
    public QryResult addCommentByFeedId(
            Long feedId,
            Long parentId,
            Long userId,
            String content
    ) {

        CommentDTO commentDTO = CommentDTO.builder()
                .commentFeedId(feedId)
                .commentContent(content)
                .parentId(parentId)
                .user(UserDTO.builder().userId(userId).build())
                .build();

        int cnt = commentRepository.addComment(commentDTO);
        String status = "FAIL";

        if(cnt > 0) status = "OK";

        return QryResult.builder()
                .count(cnt)
                .status(status)
                .build();
    }

    @Override
    public QryResult deleteCommentById(Long commentId) {
        int cnt = commentRepository.deleteComment(commentId);
        String status = "FAIL";

        if(cnt > 0) status = "OK";

        return QryResult.builder()
                .count(cnt)
                .status(status)
                .build();
    }

    @Override
    public QryCommentList list(Long feedId) {
        QryCommentList list = new QryCommentList();

        List<CommentDTO> comments = commentRepository.findCommentsByFeedId(feedId);
        int cnt = comments.size();
        for(var comment : comments) {
            comment.setReplyList(commentRepository.findRepliesByFeedId(comment.getCommentId()));
            cnt += comment.getReplyList().size();
        }

        list.setCount(cnt);
        list.setList(comments);
        list.setStatus("OK");

        return list;
    }

}
