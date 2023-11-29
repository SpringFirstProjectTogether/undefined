package com.lec.spring.domain.community;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OuterCommentDTO {
    Long outerCommentId;
    Long outerUserId;
    String outerContent;
    String writer;
    List<InnerCommentDTO> innerCommentDTOList;
}
