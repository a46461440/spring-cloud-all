package com.zxc.common.java8;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 *
 * @see LocalDate
 * @see LocalDateTime
 * @see LocalTime
 * test
 *
 * {@link LocalDateTime},{@link LocalDate},{@link LocalTime}和{@link Date}的转换都是通过
 * {@link ZoneId}和{@link Instant}作为中转完成,以上三个类在进行转换的时候都需要设置{@link ZoneId}
 * {@link Date}在进行转换的时候不需要设置{@link ZoneId}
 *
 * @author Zhou RunMing
 * @Date 2019-1-14
 */
@Slf4j
public class DateTime {

    @Test
    public void testLocalDate() {
        LocalDate localDate = LocalDate.now();
        this.log.info(localDate.toString());
        //格式化
        this.log.info(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        this.log.info(localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        //与Date进行转换
        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        this.log.info(date.toString());
        Date dateTo = new Date();
        this.log.info(LocalDateTime.ofInstant(dateTo.toInstant(),ZoneId.systemDefault()).toLocalDate()
                .plusDays(2).minusDays(1).toString());
        //下个周五
        this.log.info(localDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY)).toString());
        //第五个月的今天这个号
        this.log.info(localDate.withMonth(5).toString());
        //这个月的一号
        this.log.info(localDate.with(TemporalAdjusters.firstDayOfMonth()).toString());
        this.log.info(localDate.with(TemporalAdjusters.lastDayOfMonth()).toString());
    }

    @Test
    public void testLocalDateTime() {
        LocalDateTime date = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
        this.log.info(date.toString());
        this.log.info(date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss")).toLowerCase());
        //强行改变小时
        this.log.info(date.withHour(2).toString());
        //明年第一天
        this.log.info(date.with(TemporalAdjusters.firstDayOfNextYear()).toString());
    }
}
