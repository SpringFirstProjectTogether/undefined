package com.lec.spring.repository.community;

import com.lec.spring.domain.community.CommentDTO;

import java.util.List;

public interface CommentRepository {

    // feed id 로 해당 피드의 댓글 찾기
    List<CommentDTO> findCommentsByFeedId(Long id);

    // 댓글 작성
    int addComment(CommentDTO commentDTO);

    // 댓글 삭제
    int deleteComment(Long commentId);


    // 댓글 수정?? => 기획에는 안 넣긴 했음
}
