package cn.zhangxiang.mychat.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangxiang
 * @date 2024-09-03 15:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoomDTO {
    private String roomName;
}

