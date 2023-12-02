package com.lec.spring.service.community;

public interface CommentService {
    int addCommentByFeedId(Long feedId, String content);

    int deleteCommentById(Long commentId);
}
