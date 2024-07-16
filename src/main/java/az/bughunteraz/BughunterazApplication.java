package az.bughunteraz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.example.bughunteraz.service.email"})
public class BughunterazApplication {

    public static void main(String[] args) {
        SpringApplication.run(BughunterazApplication.class, args);
    }
}

