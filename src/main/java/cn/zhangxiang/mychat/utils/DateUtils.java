package cn.zhangxiang.mychat.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author zhangxiang
 * @date 2024-08-23 16:25
 */
@Slf4j
public class DateUtils {

    /**
     * date 转 localDateTime
     * @author zhangxiang
     * @date 2024/8/26 9:24
     */
    public static LocalDateTime dateToLocalDateTime(Date date){
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = date.toInstant().atZone(zoneId);
        return zonedDateTime.toLocalDateTime();
    }

    /**
     * localDateTime 转 date
     * @author zhangxiang
     * @date 2024/8/26 9:27
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime){
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zoneId).toInstant();
        return Date.from(instant);
    }

    /**
     * 当前时间增加多少分钟
     * @author zhangxiang
     * @date 2024/8/26 9:31
     */
    public static Date plusMinute(Date date,Long minute){
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime plusTime = localDateTime.plusMinutes(minute);
        return localDateTimeToDate(plusTime);
    }

    /**
     * 当前时间减少多少分钟
     * @author zhangxiang
     * @date 2024/8/26 9:34
     */
    public static Date minusMinute(Date date,Long minute){
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime minusTime = localDateTime.minusMinutes(minute);
        return localDateTimeToDate(minusTime);
    }

    public static void main(String[] args) {
        Date date = new Date();
        Date plusMinute = plusMinute(date, 30L);
        Date minusMinute = minusMinute(date, 30L);
        log.info("增加：{}，减少：{}",plusMinute,minusMinute);
    }
}

