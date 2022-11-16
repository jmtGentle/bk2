package com.zf.bk2.mapper;

import com.zf.bk2.model.Book;
import com.zf.bk2.vo.BookVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMapper {

    int deleteAll(Book record);

    int deleteByPrimaryKey(Long bookId);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Long bookId);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    List<BookVo> list(Book book);
}