package cn.zhangxiang.mychat.service.Impl;

import cn.zhangxiang.mychat.mapper.OnlineUserRoomMapper;
import cn.zhangxiang.mychat.pojo.entity.OnlineUserRoom;
import cn.zhangxiang.mychat.service.OnlineUserRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangxiang
 * @date 2024-09-02 16:48
 */
@Service
public class OnlineUserRoomServiceImpl implements OnlineUserRoomService {

    @Autowired
    private OnlineUserRoomMapper onlineUserRoomMapper;

    @Override
    public List<Long> selectRoomUserByUserId(Long userId) {
        List<OnlineUserRoom> source = onlineUserRoomMapper.selectByUserId(userId);
        return source.stream().map(OnlineUserRoom::getUserId).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void joinOnlineRoom(String sUserId, Long roomId) {
        long userId = Long.parseLong(sUserId);
        onlineUserRoomMapper.delUserOnlineRoom(userId);
        onlineUserRoomMapper.insertUserOnlineRoom(userId, roomId);
    }
}

