package cn.zhangxiang.mychat.mapper;

import cn.zhangxiang.mychat.pojo.entity.Room;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zhangxiang
 * @date 2024-09-03 15:47
 */
@Mapper
public interface RoomMapper {

    /**
     * 查找所有房间
     * @author zhangxiang
     * @date 2024/9/3 15:54
     */
    List<Room> selectAllRoom();

    /**
     * 创建房间
     * @author zhangxiang
     * @date 2024/9/3 16:13
     */
    void createRoom(Room info);
}
