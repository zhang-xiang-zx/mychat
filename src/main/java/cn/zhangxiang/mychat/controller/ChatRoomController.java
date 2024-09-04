package cn.zhangxiang.mychat.controller;

import cn.zhangxiang.mychat.pojo.dto.CreateRoomDTO;
import cn.zhangxiang.mychat.pojo.entity.Room;
import cn.zhangxiang.mychat.service.ChatRoomService;
import cn.zhangxiang.mychat.service.OnlineUserRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangxiang
 * @date 2024-08-30 17:26
 */
@RestController
@RequestMapping("room")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private OnlineUserRoomService onlineUserRoomService;

    @PostMapping("create")
    public void createRoom(@RequestBody CreateRoomDTO dto,
                           @RequestAttribute("userId") String userId) {
        chatRoomService.createRoom(dto, userId);
    }

    @GetMapping("join")
    public void joinOnlineRoom(@RequestParam("roomId") Long roomId,
                               @RequestAttribute("userId") String userId) {
        onlineUserRoomService.joinOnlineRoom(userId, roomId);
    }

    @GetMapping("allRoom")
    public List<Room> allRoom(){
        return chatRoomService.selectAllRoom();
    }
}

