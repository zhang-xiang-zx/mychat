package cn.zhangxiang.mychat.utils;

import java.util.List;

/**
 * 判断是否为空
 * @author zhangxiang
 * @date 2024-08-23 15:28
 */
public class Assert {
    public static Boolean isNull(Object obj) {
        return obj == null;
    }

    public static Boolean isNull(Object... objs) {
        if (objs == null) {
            return true;
        }
        for (Object obj : objs) {
            if (!isNull(obj)) {
                return false;
            }
        }
        return true;
    }

    public static Boolean notNull(Object obj) {
        return !isNull(obj);
    }

    public static Boolean notEmpty(List<?> list) {
        if (isNull(list)) {
            return false;
        }
        return !list.isEmpty();
    }
}

