package com.codve;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;

import static java.time.ZoneId.SHORT_IDS;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author admin
 * @date 2019/12/15 14:38
 */
@Slf4j
public class LocalDateTest {

    @Test
    void test1() {
        LocalDate date = LocalDate.parse("2019-12-31");
        log.info("{}", date);
    }

    @Test
    void test2() {
        LocalTime time = LocalTime.now();
        log.info("{}", time.getHour());
    }

    @Test
    void test3() {
        Exception ex = assertThrows(RuntimeException.class, () -> {
            LocalTime time = LocalTime.parse("12:31:88");
            log.info("{}", time);
        });
    }

    @Test
    void test4() {
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDate date = dateTime.toLocalDate();
        LocalTime time = dateTime.toLocalTime();
        log.info("{}", dateTime);
    }

    @Test
    void test5() {
        long ts = Instant.now().getEpochSecond();
        log.info("{}", ts);
    }

    @Test
    void test6() {
        Instant instant = Instant.ofEpochSecond(1576393298, 0);
        log.info("{}", instant.getEpochSecond());
    }

    @Test
    void test7() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTime.format(formatter));
    }

    @Test
    void instant2LocalDateTime() {
        Instant instant = Instant.ofEpochSecond(1576393298);
        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, zoneId);
        System.out.println(dateTime);
    }

    @Test
    void testFormat() {
        String timeStr = "2019-12-31 20:20:20";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(timeStr, formatter);
        System.out.println(dateTime);
    }

    @Test
    void test8() {
        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        LocalDateTime dateTime = LocalDateTime.of(2018, 12, 31, 20, 20, 20);
        Instant instant = dateTime.atZone(zoneId).toInstant();
        System.out.println(instant.getEpochSecond());
    }
}
