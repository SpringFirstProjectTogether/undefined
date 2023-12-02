package com.lec.spring.repository.community;

import com.lec.spring.domain.community.ReplyDTO;

import java.util.List;

public interface ReplyRepository {
    // 댓글 id로 대댓글 찾기
    List<ReplyDTO> findReplyByParentID(Long parentId);

    int addReplyComment(ReplyDTO replyDTO);

    int deleteReplyCommentById(Long replyId);


    // 댓글 수정?? => 기획에는 안 넣긴 했음
}
