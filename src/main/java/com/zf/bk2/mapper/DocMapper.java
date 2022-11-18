package com.zf.bk2.mapper;

import com.zf.bk2.model.Doc;
import org.springframework.stereotype.Repository;

@Repository
public interface DocMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Doc record);

    int insertSelective(Doc record);

    Doc selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Doc record);

    int updateByPrimaryKey(Doc record);
}