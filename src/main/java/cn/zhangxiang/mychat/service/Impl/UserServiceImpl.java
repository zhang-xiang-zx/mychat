package cn.zhangxiang.mychat.service.Impl;

import cn.zhangxiang.mychat.config.exception.MyException;
import cn.zhangxiang.mychat.mapper.UserMapper;
import cn.zhangxiang.mychat.pojo.dto.UserLoginDTO;
import cn.zhangxiang.mychat.pojo.dto.UserRegisterDTO;
import cn.zhangxiang.mychat.pojo.entity.User;
import cn.zhangxiang.mychat.service.UserService;
import cn.zhangxiang.mychat.utils.Assert;
import cn.zhangxiang.mychat.utils.EncryptionAndDecryption;
import cn.zhangxiang.mychat.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author zhangxiang
 * @date 2024-08-23 11:36
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String register(UserRegisterDTO dto) {
        String phone = dto.getPhone();
        String password = dto.getPassword();
        String resultMsg;
        User user = userMapper.selectUserByPhone(phone);
        if(Assert.isNull(user)){
            String encryptionPassword = EncryptionAndDecryption.encryptionPassword(password);

            dto.setPassword(encryptionPassword);
            dto.setCreateTime(new Date());
            userMapper.register(dto);
            resultMsg = "注册成功";
        }else {
            resultMsg = "该号码已被注册";
            throw new MyException(4001,resultMsg);
        }
        return resultMsg;
    }

    @Override
    public String login(UserLoginDTO dto) {
        String account = dto.getAccount();
        User user = userMapper.selectUserByPhone(account);
        String token;
        if(Assert.notNull(user)){
            String password = user.getPassword();
            String dtoPassword = dto.getPassword();
            Boolean isSuccess = EncryptionAndDecryption.decryptionPassword(dtoPassword, password);
            if(isSuccess){
                Long userId = user.getUserId();
                String nickName = user.getNickName();
                token = JwtUtils.createToken(userId, nickName);
            }else{
                throw new MyException(4002,"账号或密码错误");
            }
        }else{
            throw new MyException(4002,"账号或密码错误");
        }
        return token;
    }

    @Override
    public User selectUserById(String userId) {
        long uId = Long.parseLong(userId);
        return userMapper.selectUserById(uId);
    }
}

