package cn.zhangxiang.mychat.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * @author zhangxiang
 * @date 2024-08-23 11:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserRegisterDTO {

    private Long userId;

    private String nickName;

    private String account;

    private String password;

    private Date createTime;
}

