package cn.zhangxiang.mychat.config.exception;

/**
 * @author zhangxiang
 * @date 2024-08-30 12:02
 */
public class MyException extends RuntimeException {
    private int code;

    public MyException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}


