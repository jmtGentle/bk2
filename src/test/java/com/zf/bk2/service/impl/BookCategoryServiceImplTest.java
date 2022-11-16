package com.zf.bk2.service.impl;

import com.zf.bk2.model.BookCategory;
import com.zf.bk2.model.User;
import com.zf.bk2.service.BaseTestCase;
import com.zf.bk2.service.IBookCategoryService;
import com.zf.bk2.util.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class BookCategoryServiceImplTest extends BaseTestCase {
    @Autowired
    private IBookCategoryService bookCategoryService;

    private BookCategory bookCategory;

    @Before
    public  void  before(){
        bookCategory = new BookCategory();
    }

    @Test
    public void delete() {
        bookCategory.setBookCategoryId(12l);
        bookCategoryService.delete(bookCategory.getBookCategoryId());
    }

    @Test
    public void insert() {
        bookCategory.setBookCategoryName("俄罗斯");
        bookCategoryService.insert(bookCategory);
    }

    @Test
    public void load() {
        bookCategory.setBookCategoryId(12l);
        bookCategoryService.load(bookCategory.getBookCategoryId());
    }

    @Test
    public void update() {
        bookCategory.setBookCategoryName("佛法");
        bookCategory.setBookCategoryId(11l);
        bookCategoryService.update(bookCategory);
    }

    @Test
    public void list() {
        PageBean pageBean = new PageBean();
        pageBean.setPage(1);
        pageBean.setRows(7);
        List<BookCategory> bookCategoryList = bookCategoryService.list(bookCategory,pageBean);
        for (BookCategory b : bookCategoryList ){
            System.out.println(b);
        }
        System.out.println(pageBean);
    }
}