package com.zf.bk2.service;

import com.zf.bk2.model.Book;
import com.zf.bk2.util.PageBean;
import com.zf.bk2.vo.BookVo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@CacheConfig
public interface IBookService {

    int insert(Book record);

    int delete(Long bookId);

    int deleteAll(Book record);

    int update(Book record);

    @Transactional(readOnly = true)
    Book load(Long bookId);

//    @Cacheable(value = "abc")
    @Transactional(readOnly = true)
    List<BookVo> list(BookVo bookVo, PageBean pageBean);


    int updatePrice(Book book);

    int updateBookImage(Book book);

    Book loadBook(Book book);

    Book update1(Book book);
}
