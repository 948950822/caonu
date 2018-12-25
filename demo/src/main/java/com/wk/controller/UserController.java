package com.wk.controller;

import com.wk.model.User;
import com.wk.service.IUserService;
import com.wk.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("login")
    public String login(){
        return  "show";
    }

    @RequestMapping("queryUserList")
    @ResponseBody
    public List<User> queryUserList(){
        List<User> users = userService.queryUserList();
        return  users;
    }
    //文件上传
    @RequestMapping("upFile")
    @ResponseBody
    public String setUploaddify(HttpServletRequest request,
                                MultipartFile image) {
        String path = FileUtil.FileUpload(image, request);
        if(path.contains("null")) {
            return null;
        }else {
            return path;
        }
    }
    //新增User
    @RequestMapping("insertUser")
    @ResponseBody
    public int insertUser(User user){
         userService.addUser(user);
         return  1;
    }
    //根据Id获取对象
    @RequestMapping("queryUserById")
    @ResponseBody
    public User queryUserById(Integer id){
        User user = userService.queryUserById(id);
        return  user;
    }
    //删除
    @RequestMapping("deletes")
    @ResponseBody
    public int deletes(String ids){
        String[] str = ids.split(",");
        for (int i = 0 ; i < str.length ; i ++){
            User user = userService.queryUserById(Integer.parseInt(str[i]));
            userService.deleteUser(user);
        }
        return  1;
    }

}
