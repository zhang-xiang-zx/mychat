package cn.zhangxiang.mychat.service;

import java.util.List;

/**
 * @author zhangxiang
 * @date 2024-09-02 16:48
 */
public interface OnlineUserRoomService {

    /**
     * 查找跟xx用户所在用一房间的用户，不包括xx用户
     * @author zhangxiang
     * @date 2024/9/2 17:54
     */
    List<Long> selectRoomUserByUserId(Long userId);
}
