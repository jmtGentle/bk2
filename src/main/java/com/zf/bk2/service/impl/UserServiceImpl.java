package com.zf.bk2.service.impl;

import com.zf.bk2.mapper.UserMapper;
import com.zf.bk2.model.Book;
import com.zf.bk2.model.User;
import com.zf.bk2.service.IUserService;
import com.zf.bk2.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    public void UserMapper(){

    }

    @Override
    public int delete(Long userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }



    @Override
    public int update(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public int forgetUserPwd(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<User> list(User user, PageBean pageBean) {
        //设置分页处理
//        if (null != pageBean) {
//            PageHelper.startPage(pageBean.getPage(), pageBean.getRows());
//        }
//
        List<User> userList =  userMapper.list(user);
//        System.out.println(bookList.getClass().getName());
//        PageInfo pageInfo = new PageInfo(bookList);
////        System.out.println("页码：" + pageInfo.getPageNum());
////        System.out.println("页大小：" + pageInfo.getPageSize());
////        System.out.println("总记录：" + pageInfo.getTotal());
//        pageBean.setTotal(pageBean.getTotal() + "");
        return  userList;
    }

    @Override
    public User select(User user) {
        return userMapper.select(user);
    }

    @Override
    public User loadUser(User user) {
        return userMapper.loadUser(user);
    }

    @Override
    public User loadUserName(User user) {
        return userMapper.loadUserName(user);
    }
}
