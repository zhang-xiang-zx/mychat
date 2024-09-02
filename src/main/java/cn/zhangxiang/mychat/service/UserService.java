package cn.zhangxiang.mychat.service;

import cn.zhangxiang.mychat.pojo.dto.UserLoginDTO;
import cn.zhangxiang.mychat.pojo.dto.UserRegisterDTO;
import cn.zhangxiang.mychat.pojo.entity.User;

/**
 * @author zhangxiang
 * @date 2024-08-23 11:34
 */
public interface UserService {

    /**
     * 用户注册
     * @author zhangxiang
     * @date 2024/8/23 11:35
     */
    String register(UserRegisterDTO dto);

    /**
     * 用户登录
     * @author zhangxiang
     * @date 2024/8/30 10:45
     */
    String login(UserLoginDTO dto);

    User selectUserById(String userId);
}
