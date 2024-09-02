package cn.zhangxiang.mychat.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import java.util.UUID;

/**
 * 加密：MD5 + 盐
 * @author zhangxiang
 * @date 2024-08-23 14:23
 */
@Slf4j
public class EncryptionAndDecryption {

    /**
     * 加密密码
     * @author zhangxiang
     * @date 2024/8/30 10:48
     */
    public static String encryptionPassword(String password){
        String salt = UUID.randomUUID().toString().substring(0, 16);
        String addSalt = salt + password;
        String encryptionPassword = encryptionMD5(addSalt);
        return salt + encryptionPassword;
    }

    /**
     * 对比密码
     * @author zhangxiang
     * @date 2024/8/30 10:48
     */
    public static Boolean decryptionPassword(String password, String sourcePassword){
        String salt = sourcePassword.substring(0, 16);
        String addSalt = salt + password;
        String enPassword = encryptionMD5(addSalt);
        String data = salt + enPassword;
        return data.equals(sourcePassword);
    }

    private static String encryptionMD5(String data){
        return DigestUtils.md5DigestAsHex(data.getBytes());
    }
}

