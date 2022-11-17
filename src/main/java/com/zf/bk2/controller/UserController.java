package com.zf.bk2.controller;

import com.zf.bk2.model.User;
import com.zf.bk2.service.IBookService;
import com.zf.bk2.service.IUserService;
import com.zf.bk2.util.JsonData;
import com.zf.bk2.util.JsonUtils;
import com.zf.bk2.util.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static java.lang.System.out;

@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    private User user = new User();


    @RequestMapping("/toList")
    @ResponseBody
    public String toList( HttpServletRequest request, PageBean pageBean){
        System.out.println("开始分页查询");
        //从前端获取需要搜索的用户名
        String userName = request.getParameter("userName");
        user.setUserName(userName);
        out.println("从前端获取的userName:"+userName);

        List<User> userList = userService.list(user, pageBean);

        //开始进行分页
        out.println("进入分页");
        JsonData jsonData = new JsonData();
        jsonData.setCode(0);
        jsonData.setMessage("用户查询成功");
        jsonData.setResult(userList);
        out.println("查询getPage"+pageBean.getPage());
        out.println("查询getRows"+pageBean.getRows());
        jsonData.setPage(pageBean.getPage());
        jsonData.setRows(pageBean.getRows());
        jsonData.setTotal(pageBean.getTotal());

//        System.out.println("书本查询成功"+jsonData);
        String JsonDate = JsonUtils.getJson(jsonData);
        return JsonDate;
    }

    @RequestMapping("/todelete")
    @ResponseBody
    public String delte( HttpServletRequest request){
        System.out.println("进入删除");
       String userId = request.getParameter("userId");
       Long longuserId = Long.parseLong(userId);
        System.out.println(userId);

        userService.delete(longuserId);
        JsonData jsonData = new JsonData();
        jsonData.setCode(0);
        jsonData.setMessage("用户删除成功");
        String JsonDate = JsonUtils.getJson(jsonData);
        return  JsonDate;
    }

    @RequestMapping("/toInsert")
    @ResponseBody
    public String toInsert( HttpServletRequest request){
        out.println("进入添加用户");

        String userName = request.getParameter("userName");
        out.println("查询userName："+userName);
        String userPwd = request.getParameter("userPwd");
        out.println("查询userPwd："+userPwd);
//        String checkuserPwd = request.getParameter("checkuserPwd");
        String userType = request.getParameter("userType");
        out.println("查询userType："+userType);

        //将不同的数据进行转换

        int intUserType = Integer.parseInt(userType);

        user.setUserName(userName);
        user.setUserPwd(userPwd);
        user.setUserType(intUserType);
        userService.insert(user);

        JsonData jsonData = new JsonData();

            jsonData.setCode(0);
            jsonData.setMessage("用户新增成功");
            out.println("用户新增成功:" + jsonData);


        String jsonData1 = JsonUtils.getJson(jsonData);
        return  jsonData1;
    }


    @RequestMapping("/toForgetUserPwd")
    @ResponseBody
    public String toForgetUserPwd( HttpServletRequest request){
        out.println("进入toForgetUserPwd");
        JsonData jsonData = new JsonData();
        out.println("进入");
//        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        String userPwd = request.getParameter("userPwd");
        String userType = request.getParameter("userType");
        int intuserType = Integer.parseInt(userType);
        out.println("从前端传来的username:"+userName);
        user.setUserName(userName);
        out.println("从前端传来的userPwd:"+userPwd);
        user.setUserPwd(userPwd);
        out.println("查看是否赋值成功！"+user.getUserName());
        User user1 = userService.loadUserName(user);
        out.println("查看是否成功获取："+user1);
        if (null==user1) {
            jsonData.setCode(1);
            jsonData.setMessage("账号不存在");
            out.println("账号不存在:" + jsonData);
        }else if (intuserType != user1.getUserType()){
            jsonData.setCode(1);
            jsonData.setMessage("用户类别输入错误");
            out.println("用户类别输入错误:" + jsonData);
        }else {
            //修改
            out.println("查看userid："+user1.getUserId());
            user.setUserId(user1.getUserId());
//            user.setUserPwd(userPwd);
            user.setUserPwd(userPwd);
            out.println("是否成功获取到userPwd:"+user.getUserPwd());
            out.println("查看所需要修改的数据："+user);
            int i = userService.forgetUserPwd(user);
            out.println("修改后的数据："+i);
            jsonData.setMessage("用户密码修改成功");
            jsonData.setCode(0);
            out.println("用户密码修改成功:" + jsonData);
        }
        String jsonData1 = JsonUtils.getJson(jsonData);
        return  jsonData1;
    }

    @RequestMapping("toUpdate")
    @ResponseBody
    public  String update(HttpServletRequest request){
        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        String userPwd = request.getParameter("userPwd");
        String userType = request.getParameter("userType");

        //数据转换
        long longUserId = Long.parseLong(userId);
        int intUserType = Integer.parseInt(userType);

        user.setUserId(longUserId);
        out.println("获取userId"+longUserId);
        user.setUserName(userName);
        out.println("获取userName"+userName);
        user.setUserPwd(userPwd);
        out.println("获取userPwd"+userPwd);
        user.setUserType(intUserType);
        out.println("获取intUserType"+intUserType);
        out.println("输出获取的所有数据"+user);

        JsonData jsonData = new JsonData();
        User user1 = userService.select(user);
//        if(null==user1){
//            jsonData.setCode(1);
//            out.println("前端修改的值后端不存在:"+jsonData);
//            jsonData.setMessage("后端不存在该值,无法修改");
//        }elseuser1.getUserId()!=longUserId
            if (null==user1){
            jsonData.setCode(1);
            jsonData.setMessage("Id值不存在，并且Id无法被修改");
            out.println("输出Id:"+longUserId);
        }else if (1 !=intUserType&&0!=intUserType){
            jsonData.setCode(1);
            jsonData.setMessage("用户类别不对:"+jsonData);
            out.println("userType类别不正确");
        }else{
            out.println("进入修改");
            out.println("查看前端userid："+user.getUserId());
            user.setUserId(user1.getUserId());
                out.println("查看赋值后userid："+user1.getUserId());
//            user.setUserName(user1.getUserName());
                user1.setUserName(userName);
                out.println("查看赋值后userid："+user1.getUserName());
//            user.setUserPwd(userPwd);
                user1.setUserPwd(userPwd);
                out.println("查看赋值后userPwd："+user1.getUserPwd());
//            user.setUserType(user1.getUserType());
                user1.setUserType(intUserType);
                out.println("查看赋值后userType："+user1.getUserType());
            int i = userService.update(user);
            out.println(i);
            jsonData.setMessage("用户密码修改成功");
            jsonData.setCode(0);
            out.println("用户密码修改成功:" + jsonData);

        }


        String jsonData1 = JsonUtils.getJson(jsonData);
        return  jsonData1;
    }

    @RequestMapping("/toLogin")
    @ResponseBody
    public  String toLogin(HttpServletRequest request){

        String userName = request.getParameter("userName");
        String userPwd = request.getParameter("userPwd");

        //获取值
        user.setUserName(userName);
        user.setUserPwd(userPwd);

        JsonData jsonData = new JsonData();


        //调用单个查询的方法
        User user1 = userService.loadUser(user);
        if (null == user1){
            jsonData.setCode(-1);
            jsonData.setMessage("用户查询失败");
            jsonData.setMessage("用户密码或用户名错误");

        }else {
            jsonData.setCode(0);
            jsonData.setMessage("用户登录成功");
        }

        String jsonDate = JsonUtils.getJson(jsonData);
        return jsonDate;
    }
}
