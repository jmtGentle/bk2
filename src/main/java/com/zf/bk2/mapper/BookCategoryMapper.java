package com.zf.bk2.mapper;

import com.zf.bk2.model.BookCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookCategoryMapper {
    int deleteByPrimaryKey(Long bookCategoryId);

    int insert(BookCategory record);

    int insertSelective(BookCategory record);

    BookCategory selectByPrimaryKey(Long bookCategoryId);

    int updateByPrimaryKeySelective(BookCategory record);

    int updateByPrimaryKey(BookCategory record);

    List<BookCategory> list(BookCategory bookCategory);
}