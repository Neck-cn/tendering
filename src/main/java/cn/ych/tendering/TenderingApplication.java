package cn.ych.tendering;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@MapperScan("cn.ych.tendering.mapper")
@SpringBootApplication
@EnableWebMvc
public class TenderingApplication {

    public static void main(String[] args) {
        SpringApplication.run(TenderingApplication.class, args);
    }
    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
