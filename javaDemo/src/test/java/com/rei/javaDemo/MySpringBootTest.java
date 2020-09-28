package com.rei.javaDemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 需要启动springboot的测试
 */
@SpringBootTest
@Slf4j
public class MySpringBootTest {

    @Test
    public void testNewCarHttp(){
        log.info("aaatest");
    }
}
