package cn.zhangxiang.mychat.service.Impl;

import cn.zhangxiang.mychat.mapper.UserMapper;
import cn.zhangxiang.mychat.pojo.dto.UserRegisterDTO;
import cn.zhangxiang.mychat.service.UserService;
import cn.zhangxiang.mychat.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangxiang
 * @date 2024-08-23 11:36
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String register(UserRegisterDTO dto) {
        userMapper.register(dto);
        return JwtUtils.createToken(dto.getUserId(), dto.getNickName());
    }
}

