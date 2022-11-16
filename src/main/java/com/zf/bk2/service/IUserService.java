package com.zf.bk2.service;

import com.zf.bk2.model.Book;
import com.zf.bk2.model.User;
import com.zf.bk2.util.PageBean;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@CacheConfig
public interface IUserService {
    int delete(Long userId);

    int insert(User record);

//    @Transactional(readOnly = true)
    User loadUser(User user);

    //单个查询
    User select(User user);

    User loadUserName(User user);

    int forgetUserPwd(User record);

    int update(User record);

    @Transactional(readOnly = true)
    List<User> list(User user, PageBean pageBean);
}
