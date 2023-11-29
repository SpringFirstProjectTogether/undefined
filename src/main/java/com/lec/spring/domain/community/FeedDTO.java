package com.lec.spring.domain.community;

import com.lec.spring.domain.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedDTO {
    long feedId;
    String feedTitle;
    String feedContent;
    String feedState;
    Date feedRegdate;
    int likeCnt;
    String tagList;
    UserDTO user;
}
