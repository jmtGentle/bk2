package com.zf.bk2.controller;

import com.zf.bk2.model.Book;
import com.zf.bk2.model.Doc;
import com.zf.bk2.service.IBookService;
import com.zf.bk2.service.IDocService;
import com.zf.bk2.util.JsonData;
import com.zf.bk2.util.JsonUtils;
import com.zf.bk2.util.PageBean;
import com.zf.bk2.util.UrlUtils;
import com.zf.bk2.vo.BookVo;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

import static java.lang.System.out;

@Controller
@Slf4j
@RequestMapping("/book")
public class BookController {

    //图片上传目录,要考虑linux/windows路径问题
    private String saveDir="D:\\temp\\image\\";

    @Autowired
    private IBookService bookService;

    @Autowired
    private IDocService docService;


    private  MapperFactory mapperFactory;
    private com.zf.bk2.util.UrlUtils UrlUtils;

    public  void init(Model model){
        model.addAttribute("ts",System.currentTimeMillis());
    }

    @RequestMapping("/toList")
    @ResponseBody//获取前端json数据的注释代码
    public  String toList(BookVo bookVo, PageBean pageBean, HttpServletRequest request){
        out.println("查询从前端查到的book所有的数据："+bookVo);
        out.println("查询从前端查到的pageBean所有的数据："+pageBean);
        List<BookVo> bookList = bookService.list(bookVo, pageBean);
        for (BookVo b: bookList){
            out.println("查询到数据："+b);
        }
        JsonData jsonData = new JsonData();
        jsonData.setCode(0);
        jsonData.setMessage("书本查询成功");
        jsonData.setResult(bookList);
        jsonData.setPage(pageBean.getPage());
        jsonData.setRows(pageBean.getRows());
        jsonData.setTotal(pageBean.getTotal());
        System.out.println("书本查询成功"+jsonData);
        String JsonDate = JsonUtils.getJson(jsonData);
        return JsonDate;

    }

    @RequestMapping("toMerger")
    @ResponseBody
    public  String tomerger( Book book,HttpServletRequest request){

        JsonData jsonData = new JsonData();
        jsonData.setCode(0);
        String bookId = request.getParameter("bookId");
        String bookName = request.getParameter("bookName");
        String bookCategoryId = request.getParameter("bookCategoryId");
        String bookAuthor = request.getParameter("bookAuthor");
        String bookPrice = request.getParameter("bookPrice");
        String bookImage = request.getParameter("bookImage");
        String publishing = request.getParameter("publishing");
        String bookDesc = request.getParameter("bookDesc");
        String bookState = request.getParameter("bookState");
        String salesVolume = request.getParameter("salesVolume");
        out.println("现在进入数据的转换");

        long longBookId = StringUtils.isNotBlank(bookId) ? Long.parseLong(bookId) : 0;
        out.println(longBookId);

        long longBookCategoryId = StringUtils.isNotBlank(bookCategoryId) ? Long.parseLong(bookCategoryId) :0 ;
        out.println(longBookCategoryId);

        long longBookImage = StringUtils.isNotBlank(bookImage) ? Long.parseLong(bookImage) :0 ;
        out.println(longBookImage);

        out.println("获取Long型数据:" + book.getBookId());

        //基本类型的转换 String--->float
        float floatBookPrice = Float.parseFloat(bookPrice);
        //基本类型的转换 String --->integer
        int intBookState = Integer.parseInt(bookState);
        int intsSlesVolume = Integer.parseInt(salesVolume);

        //注入数据
        book.setBookId(longBookId);
        book.setBookName(bookName);
        book.setBookCategoryId(longBookCategoryId);
        book.setBookAuthor(bookAuthor);
        book.setBookPrice(floatBookPrice);
        book.setBookImage(longBookImage);
        book.setPublishing(publishing);
        book.setBookDesc(bookDesc);
        book.setBookState(intBookState);
        book.setSalesVolume(intsSlesVolume);

        if (0 == book.getBookId()) {
            bookService.insert(book);
            jsonData.setMessage("书本新增成功");
            out.println("书本新增成功:" + jsonData);
        } else {
            bookService.update(book);
            out.println("获取修改后的:"+book);
            jsonData.setMessage("书本修改成功");
            out.println("书本修改成功:" + jsonData);
        }
        String JsonDate = JsonUtils.getJson(jsonData);
        return JsonDate;
    }

    @RequestMapping("todelete")
    @ResponseBody
    public  String delete( Book book,HttpServletRequest request){
        JsonData jsonData = new JsonData();
        jsonData.setCode(0);
        String bookId = request.getParameter("bookId");
        long longBookId =Long.parseLong(bookId);
        out.println(longBookId);
//        book.setBookId(longBookId);
        bookService.delete(longBookId);
        jsonData.setMessage("书本删除成功");
        String JsonData = JsonUtils.getJson(jsonData);
        return JsonData;
    }

    @RequestMapping("toUploadBookImage")
    @ResponseBody
    public  String uploadBookImage( HttpServletRequest request,PageBean pageBean)throws Exception{
       //获取前端的值
        String bookName = request.getParameter("bookName");
        out.println("输出从前端获取的姓名："+bookName);
        //获取前端页码页大小
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        //转换数据为int类型
        int pageInt = Integer.parseInt(page);
        int rowsInt = Integer.parseInt(rows);
        //输出页码页大小
        out.println("输出前端获取的页码："+page);
        out.println("输出从前端获取的页大小:"+rows);
        //赋值
        pageBean.setRows(pageInt);
        pageBean.setRows(rowsInt);
        //进行分页查询
        return  null;
    }

    public  String uploadBookImage(HttpServletRequest request,MultipartFile file,Doc doc,Book book)throws  Exception{
        out.println("查看img："+file);
        out.println("进入文件上传");
        //获取书本Id
        String bookId = request.getParameter("bookId");
        long bookIdLong = Long.parseLong(bookId);
        out.println("查看进入book修改页面的bookid:"+bookIdLong);
        //1.保存临时文件到指定的目录
        Long id =System.currentTimeMillis();
        String path=saveDir+id;
        File saveDirs = new File(path);
        System.out.println("查看值:" + saveDirs);
        file.transferTo(saveDirs);
        System.out.println("查看值下一步");
        //2.保存文件上传记录
        doc.setId(id);
        doc.setFileName(file.getOriginalFilename());
        doc.setMime(file.getContentType());
        docService.insert(doc);
        //更新书本封面
        book.setBookId(bookIdLong);
        book.setBookImage(id);
        Book loadBook = bookService.loadBook(book);
        out.println("查询一遍："+loadBook.getBookName());
        book.setBookName(loadBook.getBookName());

//        bookService.update1(this.book);
//        out.println("修改后id" + this.book);
        JsonData jsonData = new JsonData();
        jsonData.setCode(0);
        jsonData.setMessage("封面上传成功");
        String JsonData = JsonUtils.getJson(jsonData);
        return JsonData;
    }
}
