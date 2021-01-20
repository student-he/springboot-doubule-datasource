package com.arbitrary.controller;

import com.arbitrary.entity.User;
import com.arbitrary.service.UserService;
import com.arbitrary.tools.Response;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
//http://localhost:8089/userController/showAllUsers
@RestController
@RequestMapping(value = "/userController")
public class UserController {
    @Resource
    private UserService userService;

    //展示所有用户
    @RequestMapping("/showAllUsers")
    public Response showAllUsers(){
        List<User> userList = userService.showAllUsers();
        Response response = Response.newResponse();
        return response.setData(userList);
    }

    //根据id查询用户信息
    @RequestMapping("/showUserInfo")
    public Response showUserInfo(Integer id){
        Response response = Response.newResponse();
        User userInfo = userService.getUserByPrimarykey(id);
        return response.setData(userInfo);
    }

    //更改用户信息
    @RequestMapping("/updateUserInfo")
    public Response updateUserInfo(@RequestBody User user){
        Response response = Response.newResponse();
        if (user.getId()!=null && ! "".equals(user.getId())){
            User tempUser = userService.getUserByPrimarykey(user.getId());
            if (tempUser==null){
                return response.setCodeAndMessage(9999,"用户不存在！");
            }
            userService.updateUserInfo(user);
            return response.OK();
        }
        return response.setCodeAndMessage(9999,"更改信息失败！");
    }

    //新增用户
    @RequestMapping("/addUser")
    public Response addUser(@RequestBody User userInfo){
        Response response = Response.newResponse();
        userService.insertOneUser(userInfo);
        return response.OK();
    }
}
