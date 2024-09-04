package cn.zhangxiang.mychat.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * @author zhangxiang
 * @date 2024-09-03 15:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Room {
    private Long roomId;

    private String roomName;

    private  Long maxNum;

    private Date createTime;

    private Long createUserId;
}

