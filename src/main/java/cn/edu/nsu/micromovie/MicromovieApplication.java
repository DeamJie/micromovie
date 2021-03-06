package cn.edu.nsu.micromovie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.edu.nsu.micromovie.dao")
public class MicromovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicromovieApplication.class, args);
	}

}

