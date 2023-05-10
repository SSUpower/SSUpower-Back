package SSUpower.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"map", "other.package.to.scan"})
@ComponentScan(basePackages = {"timetable", "other.package.to.scan"})
public class PowerrangerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PowerrangerApplication.class, args);
    }

}
