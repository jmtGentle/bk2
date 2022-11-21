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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
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

    private Doc doc = new Doc();

    private Book book = new Book();

    private PageBean pageBean = new PageBean();


    private  MapperFactory mapperFactory;
    private com.zf.bk2.util.UrlUtils UrlUtils;

    public  void init(Model model){
        model.addAttribute("ts",System.currentTimeMillis());
    }

    @RequestMapping("/toList")
    @ResponseBody//获取前端json数据的注释代码
    public  String toList(BookVo bookVo,HttpServletRequest request){
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
    public  String tomerger(HttpServletRequest request){

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
    public  String delete(HttpServletRequest request){
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
    public  String uploadBookImage( HttpServletRequest request){
       //获取前端的值
        String bookName = request.getParameter("bookName");
        book.setBookName(bookName);
        out.println("输出从前端获取的姓名："+bookName);
        JsonData jsonData = new JsonData();
        BookVo bookVo = new BookVo();
        List<BookVo> docList = bookService.list(bookVo, pageBean);
        out.println("返回到前端值：" + docList);
        jsonData.setCode(0);
        jsonData.setMessage("书籍查询成功");
        jsonData.setResult(docList);
        jsonData.setPage(pageBean.getPage());
        jsonData.setRows(pageBean.getRows());
        jsonData.setTotal(pageBean.getTotal());
        String JsonData = JsonUtils.getJson(jsonData);
        out.println("查询返回前端的数据：" + JsonData);
        return JsonData;
    }

    @RequestMapping("uploadBookImage")
    @ResponseBody
    public  String uploadBookImage(HttpServletRequest request,MultipartFile file)throws  Exception{
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
        out.println("获取："+id);
        doc.setFileName(file.getOriginalFilename());
        out.println("获取："+file.getOriginalFilename());
        doc.setMime(file.getContentType());
        out.println("获取:"+file.getContentType());
        docService.insert(doc);
        out.println("获取增加后的值："+doc);
        //更新书本封面
        book.setBookId(bookIdLong);
        out.println("获取更新后的bookId:"+bookIdLong);
        book.setBookImage(id);
        out.println("获取从doc里面传过来的id赋值给bookImage："+id);
        Book loadBook = bookService.loadBook(book);
        out.println("获取查询的book:"+book);
        book.setBookName(loadBook.getBookName());
        out.println("查询一遍："+loadBook.getBookName());
        bookService.update1(book);
        out.println("修改后id" + book);
        JsonData jsonData = new JsonData();
        jsonData.setCode(0);
        jsonData.setMessage("封面上传成功");
        String JsonData = JsonUtils.getJson(jsonData);
        return JsonData;
    }

    @RequestMapping("asOpen")//查看文件上传成功的图片
    @ResponseBody
    public ResponseEntity asOpen(HttpServletRequest request, long id)throws  Exception{
        System.out.println("查看id:" + id);
        doc.setId(id);
        Doc d = docService.loadImage(id);
        out.println("对比d结果：" + d);
        String fileName = d.getFileName();
        //io读写文件
        byte[] body = null;
        String path = saveDir + d.getId();
        System.out.println(path);
        InputStream in = new FileInputStream(path);
        body = new byte[in.available()];
        in.read(body);
        HttpHeaders headers = new HttpHeaders();
        out.println("查看mime"+d.getMime());
        headers.add("Content-Type", d.getMime());
        out.println("查看fileName"+fileName);
        headers.add("Content-Disposition", ";filename=" + fileName);
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);
        out.println("最终:" + response);
        return response;
    }

    //文件下载
    @RequestMapping("/asSave")
    @ResponseBody
    public ResponseEntity asSave(Long id) throws Exception {
//        String id = request.getParameter("bookImage");
//        long longBookId = Long.parseLong(id);
        out.println("是否能获取到id："+id);
        doc.setId(id);
        Doc d = docService.loadImage(id);
        String fileName = d.getFileName();
        fileName = URLEncoder.encode(fileName, "UTF-8");

        //io读写文件
        byte[] body = null;
        String path = saveDir + d.getId();
        System.out.println(path);
        InputStream in = new FileInputStream(path);
        body = new byte[in.available()];
        in.read(body);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", d.getMime());
        headers.add("Content-Disposition", "attachment;filename=" + fileName);
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);
        return response;
    }
}
