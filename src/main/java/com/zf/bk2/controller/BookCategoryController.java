package com.zf.bk2.controller;

import com.zf.bk2.model.BookCategory;
import com.zf.bk2.service.IBookCategoryService;
import com.zf.bk2.util.JsonData;
import com.zf.bk2.util.JsonUtils;
import com.zf.bk2.util.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static java.lang.System.out;

@Slf4j
@Controller
@RequestMapping("/bookCategory")
public class BookCategoryController {

    @Autowired
    private IBookCategoryService bookCategoryService;

    @RequestMapping("/toList")
    @ResponseBody
    public String toList(BookCategory bookCategory, HttpServletRequest request,PageBean pageBean){
        System.out.println("进入查询");
        List<BookCategory> bookCategoryList = bookCategoryService.list(bookCategory,pageBean);
        JsonData jsonData = new JsonData();
        jsonData.setCode(0);
//        System.out.println("类别查询成功");
        jsonData.setMessage("类别查询成功");
        jsonData.setResult(bookCategoryList);

        jsonData.setPage(pageBean.getPage());
        jsonData.setRows(pageBean.getRows());
        jsonData.setTotal(pageBean.getTotal());

//        System.out.println("书本查询成功"+jsonData);
        String JsonDate = JsonUtils.getJson(jsonData);
        return JsonDate;

    }

    @RequestMapping("/toMerger")
    @ResponseBody
    public  String toMerger(BookCategory bookCategory,HttpServletRequest request){

        String bookCategoryId = request.getParameter("bookCategoryId");
        String bookCategoryName = request.getParameter("/bookCategoryName");

        //转换数据
        long longbookCategoryId = Long.parseLong(bookCategoryId);

        //获取数据
        bookCategory.setBookCategoryId(longbookCategoryId);
        bookCategory.setBookCategoryName(bookCategoryName);

        JsonData jsonData = new JsonData();
        jsonData.setCode(0);
        if (0 == bookCategory.getBookCategoryId()) {
            bookCategoryService.insert(bookCategory);
            jsonData.setMessage("用户新增成功");
            out.println("用户新增成功:" + jsonData);
        } else {
            bookCategoryService.update(bookCategory);
            jsonData.setMessage("用户修改成功");
            out.println("用户修改成功:" + jsonData);
        }

        String jsonData1 = JsonUtils.getJson(jsonData);
        return  jsonData1;
    }

    @RequestMapping("/todelete")
    @ResponseBody
    public  String delete(BookCategory bookCategory,HttpServletRequest request){
        String bookCategoryId = request.getParameter("bookCategoryId");
        long langbookCategoryId = Long.parseLong(bookCategoryId);

        bookCategoryService.delete(langbookCategoryId);
        JsonData jsonData = new JsonData();
        jsonData.setCode(0);
        jsonData.setMessage("类型删除成功");
        String JsonDate = JsonUtils.getJson(jsonData);
        return  JsonDate;
    }
}
