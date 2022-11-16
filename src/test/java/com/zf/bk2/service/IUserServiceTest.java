package com.zf.bk2.service;

import com.zf.bk2.model.Book;
import com.zf.bk2.model.User;
import com.zf.bk2.util.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;
@Slf4j
public class IUserServiceTest extends  BaseTestCase {

    @Autowired
    private IUserService userService;

    private User user;

    @Before
    public void before() {
        user = new User();
    }

    @Test
    public void delete(){
        user.setUserId(5l);
        userService.delete(user.getUserId());
}

    @Test
    public void insert() {
        user.setUserName("zlls");
        user.setUserPwd("234");
        user.setUserType(1);

        userService.insert(user);

    }

    @Test
    public void load() {
        user.setUserName("zs");
        user.setUserPwd("123");
        User users = userService.loadUser(user);
        System.out.println(users);
    }

    @Test
    public void select() {
        user.setUserId(1l);
        User users = userService.select(user);
        System.out.println(users);
    }

    @Test
    public void loadUserName() {
        user.setUserName("zs");
        User users = userService.loadUserName(user);
        System.out.println(users);
    }


    @Test
    public void update() {
        user.setUserId(2l);
        user.setUserName("lsss");
        user.setUserPwd("1228");
        user.setUserType(1);
        userService.update(user);
    }

    @Test
    public void forgetUserPwd() {
        user.setUserId(4l);
        user.setUserName("zl");
        user.setUserPwd("12yy");
        int i = userService.forgetUserPwd(user);
        System.out.println(i);
    }

    @Test
    public void list() {
        PageBean pageBean = new PageBean();
        pageBean.setPage(2);
        pageBean.setRows(7);
//        user.setUserName("zs");
        List<User> userList = userService.list(user,pageBean);
        for (User u : userList ){
            System.out.println(u);
        }
        System.out.println(pageBean);
    }
}