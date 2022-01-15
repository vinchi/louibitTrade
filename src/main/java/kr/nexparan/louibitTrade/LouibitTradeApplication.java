package kr.nexparan.louibitTrade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class LouibitTradeApplication {

	public static void main(String[] args) {
		SpringApplication.run(LouibitTradeApplication.class, args);
	}

}
