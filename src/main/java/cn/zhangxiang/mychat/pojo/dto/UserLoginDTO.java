package cn.zhangxiang.mychat.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author zhangxiang
 * @date 2024-08-30 10:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserLoginDTO {

    private String account;

    private String password;
}

