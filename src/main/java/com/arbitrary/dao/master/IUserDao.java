package com.arbitrary.dao.master;

import com.arbitrary.entity.User;

import java.util.List;

/**
 * @Classname UserMapper
 * @Description user的dao层
 * @Date 2020/5/26 16:40
 */
public interface IUserDao {
    User findById(Integer id);
    List<User> showAll();
    void insert(User user);
    void update(User user);
}
