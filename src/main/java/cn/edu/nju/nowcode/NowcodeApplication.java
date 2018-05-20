package cn.edu.nju.nowcode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan("cn.edu.nju.nowcode")
public class NowcodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(NowcodeApplication.class, args);
	}
}
