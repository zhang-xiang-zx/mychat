package cn.zhangxiang.mychat.service;

import cn.zhangxiang.mychat.pojo.dto.CreateRoomDTO;
import cn.zhangxiang.mychat.pojo.entity.Room;

import java.util.List;

/**
 * @author zhangxiang
 * @date 2024-08-30 17:27
 */
public interface ChatRoomService {

    /**
     * 查找所有房间
     * @author zhangxiang
     * @date 2024/9/3 15:46
     */
    List<Room> selectAllRoom();

    /**
     * 创建房间
     * @author zhangxiang
     * @date 2024/9/3 16:03
     */
    void createRoom(CreateRoomDTO dto, String userId);
}
