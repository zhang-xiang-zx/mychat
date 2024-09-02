package cn.zhangxiang.mychat.mapper;

import cn.zhangxiang.mychat.pojo.entity.OnlineUserRoom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zhangxiang
 * @date 2024-09-02 16:49
 */
@Mapper
public interface OnlineUserRoomMapper {

    /**
     * 查找跟xx用户同在一个房间的用户，不包括xx用户
     * @author zhangxiang
     * @date 2024/9/2 16:53
     */
    List<OnlineUserRoom> selectByUserId(Long userId);
}
