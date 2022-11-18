package com.zf.bk2.service;

import com.zf.bk2.model.Doc;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@CacheConfig
public interface IDocService {
    int insert(Doc record);

    Doc loadImage(Long id);
}
