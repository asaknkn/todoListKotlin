package com.example.todolistMysql

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.core.JdbcTemplate

@SpringBootApplication
class TodolistMysqlApplication {

	@Bean
	fun commandLineRunner(jdbcTemplate: JdbcTemplate) = CommandLineRunner {
		jdbcTemplate.execute("""CREATE TABLE IF NOT EXISTS task (
			id      BIGINT        PRIMARY KEY AUTO_INCREMENT,
			content VARCHAR(100)  NOT NULL,
			done    BOOLEAN       NOT NULL    DEFAULT FALSE
			)""")
	}

}

fun main(args: Array<String>) {
	runApplication<TodolistMysqlApplication>(*args)
}
