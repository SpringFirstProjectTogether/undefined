package com.lec.spring.domain.community;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// 대댓글을 위한 DTO
public class InnerCommentDTO {
    Long innerCommentId;
    Long innerUserId;
    Long outerCommentId;
    String innerContent;
    String writer;
}
