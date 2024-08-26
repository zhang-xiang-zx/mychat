package cn.zhangxiang.mychat.service;

import cn.zhangxiang.mychat.pojo.dto.UserRegisterDTO;

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
}
