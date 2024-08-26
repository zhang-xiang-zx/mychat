package cn.zhangxiang.mychat.controller;

import cn.zhangxiang.mychat.config.auth.NoLogin;
import cn.zhangxiang.mychat.pojo.dto.UserRegisterDTO;
import cn.zhangxiang.mychat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangxiang
 * @date 2024-08-23 9:42
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @NoLogin
    @PostMapping("register")
    public String register(@RequestBody UserRegisterDTO dto){
        return userService.register(dto);
    }

}

