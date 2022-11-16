package com.zf.bk2.service;

import com.zf.bk2.model.Book;
import com.zf.bk2.model.BookCategory;
import com.zf.bk2.util.PageBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface IBookCategoryService {
    int delete(Long bookCategoryId);

    int insert(BookCategory record);

    BookCategory load(Long bookCategoryId);

    int update(BookCategory record);

    @Transactional(readOnly = true)
    List<BookCategory> list(BookCategory bookCategory , PageBean pageBean);
}
