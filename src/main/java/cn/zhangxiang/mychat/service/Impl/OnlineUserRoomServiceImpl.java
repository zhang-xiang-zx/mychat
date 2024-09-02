package cn.zhangxiang.mychat.service.Impl;

import cn.zhangxiang.mychat.service.OnlineUserRoomService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangxiang
 * @date 2024-09-02 16:48
 */
@Service
public class OnlineUserRoomServiceImpl implements OnlineUserRoomService {
    @Override
    public List<Long> selectRoomUserByUserId(Long userId) {
        return null;
    }
}

