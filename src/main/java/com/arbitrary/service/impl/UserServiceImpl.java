package com.arbitrary.service.impl;

import com.arbitrary.entity.User;
import com.arbitrary.dao.master.IUserDao;
import com.arbitrary.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Resource
    private IUserDao userDao;

    @Override
    public User getUserByPrimarykey(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> showAllUsers() {
        return userDao.showAll();
    }

    @Override
    @Transactional(transactionManager = "primaryTransactionManager")
    public void insertOneUser(User user) {
        userDao.insert(user);
    }

    @Override
    @Transactional(transactionManager = "primaryTransactionManager")
    public void updateUserInfo(User user) {
        userDao.update(user);
    }
}
