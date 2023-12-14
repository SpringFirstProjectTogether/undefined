package com.lec.spring.repository.community;

import com.lec.spring.domain.community.PhotoDTO;

import java.util.List;

public interface PhotoRepository {
    public int save(PhotoDTO photo);

    List<PhotoDTO> findByFeed(Long feedId);
}
