package com.zf.bk2.service.impl;

import com.zf.bk2.mapper.BookMapper;
import com.zf.bk2.model.Book;
import com.zf.bk2.service.IBookService;
import com.zf.bk2.util.PageBean;
import com.zf.bk2.util.PinYinUtil;
import com.zf.bk2.vo.BookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    private BookMapper bookMapper;

    public void BookServiceImpl(){

    }

    @Override
    public int insert(Book record) {
        record.setBookNamePinyin(PinYinUtil.toPinyin(record.getBookName()));
        return bookMapper.insert(record);
    }

    @Override
    public int delete(Long bookId) {
        return bookMapper.deleteByPrimaryKey(bookId);
    }

    @Override
    public int deleteAll(Book record) {
        return bookMapper.deleteAll(record);
    }

    @Override
    public int update(Book record) {
        record.setBookNamePinyin(PinYinUtil.toPinyin(record.getBookName()));
        return bookMapper.updateByPrimaryKey(record);
    }

    @Override
    public Book load(Long bookId) {
        return bookMapper.selectByPrimaryKey(bookId);
    }


    @Override
    public List<BookVo> list(BookVo record, PageBean pageBean) {
        //设置分页处理
//        if (null != pageBean) {
//            PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
//        }
//
        List<BookVo> bookList =  bookMapper.list(record);
//        System.out.println(bookList.getClass().getName());
//        PageInfo pageInfo = new PageInfo(bookList);
////        System.out.println("页码：" + pageInfo.getPageNum());
////        System.out.println("页大小：" + pageInfo.getPageSize());
////        System.out.println("总记录：" + pageInfo.getTotal());
//        pageBean.setTotal(pageBean.getTotal() + "");
        return  bookList;
    }

    @Override
    public int updatePrice(Book book) {
        return bookMapper.updateByPrimaryKeySelective(book);
    }

    @Override
    public int updateBookImage(Book book) {
        return bookMapper.updateBookImage(book);
    }

    @Override
    public Book loadBook(Book book) {
        return bookMapper.loadBook(book);
    }

    @Override
    public Book update1(Book book) {
        return null;
    }
}
