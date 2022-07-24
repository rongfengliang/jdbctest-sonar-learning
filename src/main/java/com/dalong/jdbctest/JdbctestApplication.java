package com.dalong.jdbctest;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@SpringBootApplication
public class JdbctestApplication {

	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource){
		return  new DataSourceTransactionManager(dataSource);
	}
	@Bean
	public MeterRegistry metricRegistry() {
		return new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
	}
	@Bean
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:mysql://localhost:3306/gogs");
		config.setUsername("root");
		config.setPassword("dalongrong");
		config.setMaximumPoolSize(2);
		config.setMaxLifetime(5900);
		config.setKeepaliveTime(2000);
		config.setConnectionTimeout(2500);
		config.setDriverClassName("com.mysql.cj.jdbc.Driver");
		return new HikariDataSource(config);
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource){
		return  new JdbcTemplate(dataSource);
	}
	public static void main(String[] args) {
		SpringApplication.run(JdbctestApplication.class, args);
	}

}
