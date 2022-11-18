package com.zf.bk2.service;

import com.zf.bk2.model.Book;
import com.zf.bk2.util.PageBean;
import com.zf.bk2.vo.BookVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class IBookServiceTest extends BaseTestCase{

    @Autowired
    private  IBookService bookService;

    private Book book;

    @Before
    public  void  before(){
        book = new Book();
    }

    @Test
    public void insert() {
        book.setBookName("武侠传无敌");
//        book.setBookNamePinyin("WXZ");
        book.setBookCategoryId(2l);
        book.setBookAuthor("金庸武侠");

        book.setBookPrice(66f);
        book.setBookImage(2l);
        book.setPublishing("武侠出版社");

        book.setBookDesc("abcc");
        book.setBookState(1);
        book.setSalesVolume(0);

        bookService.insert(book);
        System.out.println(book);

    }

    @Test
    public void delete() {
        book.setBookId(160l);
        bookService.delete(book.getBookId());
    }

    @Test
    public void deleteAll() {
        book.setBookIds(new Integer[]{159,161});
        bookService.deleteAll(book);
    }



    @Test
    public void update() {
        book.setBookId(162l);
        book.setBookName("武侠传11");
        book.setBookNamePinyin("WXZ");
        book.setBookCategoryId(2l);
        book.setBookAuthor("金庸武侠1");

        book.setBookPrice(66f);
        book.setBookImage(2l);
        book.setPublishing("武侠出版社");

        book.setBookDesc("abccc");
        book.setBookState(1);
        book.setSalesVolume(0);

        bookService.update(book);
    }

    @Test
    public void updatePrice() {
        book.setBookId(162l);
        book.setBookPrice(77f);
        bookService.updatePrice(book);
    }

    @Test
    public void load() {
        book.setBookId(162l);
        Book b= bookService.load(book.getBookId());
        System.out.println(b);
    }

    @Test
    public void list() {
        PageBean pageBean = new PageBean();
        BookVo bookVo = new BookVo();
        pageBean.setPage(1);
        pageBean.setRows(30);
//        book.setBookName("武");
        List<BookVo> bookVoListList = bookService.list(bookVo,pageBean);
        for (BookVo b : bookVoListList ){
            System.out.println(b);
        }
        System.out.println(pageBean);
  }
}