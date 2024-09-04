package cn.zhangxiang.mychat.service.Impl;

import cn.zhangxiang.mychat.mapper.RoomMapper;
import cn.zhangxiang.mychat.pojo.dto.CreateRoomDTO;
import cn.zhangxiang.mychat.pojo.entity.Room;
import cn.zhangxiang.mychat.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author zhangxiang
 * @date 2024-08-30 17:28
 */
@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public List<Room> selectAllRoom() {
        return roomMapper.selectAllRoom();
    }

    @Override
    public void createRoom(CreateRoomDTO dto, String userId) {
        Room build = Room.builder().roomName(dto.getRoomName())
                .createUserId(Long.valueOf(userId))
                .createTime(new Date())
                .maxNum(30L).build();
        roomMapper.createRoom(build);
    }
}

