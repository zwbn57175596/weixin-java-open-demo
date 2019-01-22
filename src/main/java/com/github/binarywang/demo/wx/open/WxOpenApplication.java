package com.github.binarywang.demo.wx.open;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
@SpringBootApplication
@MapperScan("com.github.binarywang.demo.wx.open.mapper")
public class WxOpenApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(WxOpenApplication.class);

    @Autowired
    DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(WxOpenApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // start up
        log.info("WxOpenApplication started, datasource: {}", dataSource);
    }
}
