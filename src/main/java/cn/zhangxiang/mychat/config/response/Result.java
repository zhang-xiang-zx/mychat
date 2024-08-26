package cn.zhangxiang.mychat.config.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangxiang
 * @date 2024-08-26 14:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;

    private String msg;

    private T data;

    public static <T> Result<T> success(T data){
        Result<T> resultData = new Result<>();
        resultData.setCode(200);
        resultData.setMsg("success");
        resultData.setData(data);
        return resultData;
    }

    public static <T> Result<T> fail(int code, String msg){
        Result<T> resultData = new Result<>();
        resultData.setCode(code);
        resultData.setMsg(msg);
        return resultData;
    }
}

