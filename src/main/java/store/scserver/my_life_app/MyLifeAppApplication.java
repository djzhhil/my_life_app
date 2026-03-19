package store.scserver.my_life_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("store.scserver.my_life_app.mapper")
public class MyLifeAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyLifeAppApplication.class, args);
    }

}
