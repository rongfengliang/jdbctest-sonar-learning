package com.dalong.jdbctest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
public class Api {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping(value = {"/demo"})
    public Object demo() throws InterruptedException {
//        Stream<Conf> streamB = jdbcTemplate.queryForStream(
//                "select * from sys.sys_config", new BeanPropertyRowMapper<>(Conf.class))
//        System.out.println(streamB.findFirst().get().toString());
//        return "ok";

        System.out.println( jdbcTemplate.queryForStream(
                "select * from sys.sys_config", new BeanPropertyRowMapper<>(Conf.class)).findFirst().toString());
        return "ok";
//       try(Stream<Conf> streamB = jdbcTemplate.queryForStream(
//                "select * from sys.sys_config", new BeanPropertyRowMapper<>(Conf.class))){
//           System.out.println(streamB.findFirst().get().toString());
//           return "ok";
//       }
    }
}
