package cn.zhangxiang.mychat.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.Verification;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author zhangxiang
 * @date 2024-08-23 16:17
 */
@Slf4j
public class JwtUtils {

    /**
     * 创建token
     * @author zhangxiang
     * @date 2024/8/26 10:46
     */
    public static String createToken(Long userId, String nickName) {
        Date now = new Date();
        Date expiresDate = DateUtils.plusMinute(now, 30L);
        return JWT.create().withAudience(String.valueOf(userId)) // 签发对象
                .withIssuedAt(now) // 发行时间
                .withExpiresAt(expiresDate) // 有效时间
                .withClaim("nickName", nickName) // 载荷; 可以有多个
                .sign(Algorithm.HMAC256(userId + "zhang")); // 加密
    }

    /**
     * 校验token
     * @author zhangxiang
     * @date 2024/8/26 11:11
     */
    public static Boolean verifyToken(String token, String userId) {
        Boolean isOk = false;
        try {
            JWTVerifier build = JWT.require(Algorithm.HMAC256(userId + "zhang")).build();
            build.verify(token);
            isOk = true;
        } catch (Exception e) {
//            e.printStackTrace();
            log.info("token校验错误");
        }
        return isOk;
    }

    /**
     * 获取签发对象
     * @author zhangxiang
     * @date 2024/8/26 11:05
     */
    public static String getAudience(String token) {
        String audience = null;
        try {
            audience = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException e) {
            e.printStackTrace();
        }
        return audience;
    }

    /**
     * 获取token载荷值
     * @author zhangxiang
     * @date 2024/8/26 11:23
     */
    public static Claim getClaimByName(String token, String name){
        return JWT.decode(token).getClaim(name);
    }

    public static void main(String[] args) {
        String token = createToken(1L, "张祥");
        log.info(token);
        String audience = getAudience(token);
        Boolean aBoolean = verifyToken(token, "2");
        log.info("{}",aBoolean);
    }
}

