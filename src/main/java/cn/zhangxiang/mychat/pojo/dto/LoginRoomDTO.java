package cn.zhangxiang.mychat.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author zhangxiang
 * @date 2024-09-02 9:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class LoginRoomDTO {
    private String authorization;

    private Long roomId;
}

