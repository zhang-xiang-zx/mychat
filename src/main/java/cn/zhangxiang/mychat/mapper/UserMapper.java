package cn.zhangxiang.mychat.mapper;

import cn.zhangxiang.mychat.pojo.dto.UserRegisterDTO;
import cn.zhangxiang.mychat.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhangxiang
 * @date 2024-08-23 11:37
 */
@Mapper
public interface UserMapper {

    /**
     * 新增
     * @author zhangxiang
     * @date 2024/8/30 10:54
     */
    Long register(UserRegisterDTO dto);

    /**
     * 查找用户，根据手机号
     * @author zhangxiang
     * @date 2024/8/30 11:00
     */
    User selectUserByPhone(String phone);

    /**
     * 查找用户，根据id
     * @author zhangxiang
     * @date 2024/9/2 15:56
     */
    User selectUserById(Long userId);
}
