package com.lec.spring.domain.community;

import com.lec.spring.domain.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDTO {
    private Long commentId;
    private Long commentFeedId;
    private Long commentUserId;
    private String commentContent;

    private List<ReplyDTO> replyDTOList;
    private UserDTO user;
}
