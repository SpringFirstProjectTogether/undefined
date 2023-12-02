package com.lec.spring.service.community;

public interface ReplyService {
    int addReplyByParentId(Long parentId, Long feedId, String content);

    int deleteReplyById(Long innerCommentId);
}
