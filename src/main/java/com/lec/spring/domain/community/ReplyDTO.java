package com.lec.spring.domain.community;

import com.lec.spring.domain.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// 대댓글을 위한 DTO
public class ReplyDTO {
    private Long replyId;
    private Long replyUserId;
    private Long replyFeedId;
    private Long commentId;
    private String replyContent;

    private UserDTO user; // 작성자
}
