package com.lec.spring.repository.community;

import com.lec.spring.domain.community.TagDTO;

import java.util.List;
import java.util.Set;

public interface TagRepository {
    Set<Long> feedIdListByTag(String tag);

    TagDTO findTagByName(String tag);

    int addTag(TagDTO tag);

    int addTagAndFeed(Long feedId, Long tagId);

    List<Long> findTagIdByFeedIDd(Long feedId);

    int deleteTagAndFeed(Long feedId, Long id);
}
