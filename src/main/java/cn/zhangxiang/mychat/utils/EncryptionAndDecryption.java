package cn.zhangxiang.mychat.utils;

import org.springframework.util.DigestUtils;
import java.util.UUID;

/**
 * 加密：MD5 + 盐
 * @author zhangxiang
 * @date 2024-08-23 14:23
 */
public class EncryptionAndDecryption {
    public static String encryptionPassword(String password){
        String salt = UUID.randomUUID().toString().substring(0, 16);
        String data = password + salt;
        return encryptionMD5(data);
    }

    public static Boolean decryptionPassword(String password, String sourcePassword){
        String salt = sourcePassword.substring(0, 16);
        String data = password + salt;
        String encryptionData  = encryptionMD5(data);
        return encryptionData.equals(sourcePassword);
    }

    private static String encryptionMD5(String data){
        return DigestUtils.md5DigestAsHex(data.getBytes());
    }
}

