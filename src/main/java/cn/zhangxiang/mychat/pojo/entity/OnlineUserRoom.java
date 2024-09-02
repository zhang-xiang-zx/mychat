package cn.zhangxiang.mychat.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangxiang
 * @date 2024-09-02 16:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OnlineUserRoom {
    private Long onlineId;

    private Long userId;

    private Long roomId;
}

