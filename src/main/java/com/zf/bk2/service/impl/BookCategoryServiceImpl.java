package com.zf.bk2.service.impl;

import com.zf.bk2.mapper.BookCategoryMapper;
import com.zf.bk2.model.BookCategory;
import com.zf.bk2.model.User;
import com.zf.bk2.service.IBookCategoryService;
import com.zf.bk2.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCategoryServiceImpl implements IBookCategoryService {

    @Autowired
    private BookCategoryMapper bookCategoryMapper;

    @Override
    public int delete(Long bookCategoryId) {
        return bookCategoryMapper.deleteByPrimaryKey(bookCategoryId);
    }

    @Override
    public int insert(BookCategory record) {
        return bookCategoryMapper.insert(record);
    }

    @Override
    public BookCategory load(Long bookCategoryId) {
        return bookCategoryMapper.selectByPrimaryKey(bookCategoryId);
    }

    @Override
    public int update(BookCategory record) {
        return bookCategoryMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<BookCategory> list(BookCategory bookCategory, PageBean pageBean) {
        List<BookCategory> bookCategoryList =  bookCategoryMapper.list(bookCategory);
        return  bookCategoryList;
    }
}
