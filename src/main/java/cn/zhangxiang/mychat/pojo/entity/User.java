package cn.zhangxiang.mychat.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * @author zhangxiang
 * @date 2024-08-22 10:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class User {

    private Long userId;

    private String nickName;

    private String account;

    private String password;

    private String phone;

    private Date createTime;
}

