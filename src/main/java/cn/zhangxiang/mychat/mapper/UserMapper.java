package cn.zhangxiang.mychat.mapper;

import cn.zhangxiang.mychat.pojo.dto.UserRegisterDTO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhangxiang
 * @date 2024-08-23 11:37
 */
@Mapper
public interface UserMapper {

    Long register(UserRegisterDTO dto);
}
