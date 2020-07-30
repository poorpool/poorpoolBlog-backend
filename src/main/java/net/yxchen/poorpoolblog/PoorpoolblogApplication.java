package net.yxchen.poorpoolblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("net.yxchen.poorpoolblog.dao")
@SpringBootApplication
public class PoorpoolblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(PoorpoolblogApplication.class, args);
    }

}
