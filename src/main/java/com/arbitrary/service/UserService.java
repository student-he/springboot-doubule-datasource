package com.arbitrary.service;

import com.arbitrary.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    User getUserByPrimarykey(Integer id);

    List<User> showAllUsers();

    void insertOneUser(User user);

    void updateUserInfo(User user);
}
