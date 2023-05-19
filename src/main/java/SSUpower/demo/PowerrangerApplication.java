package SSUpower.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"map","timetable","privacy/controller"})
//@ComponentScan({"map", "timetable", "privacy"})
//@ComponentScan({"map", "timetable", "privacy.controller"})
//@ComponentScan(basePackages = {"timetable", "other.package.to.scan"})
public class PowerrangerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PowerrangerApplication.class, args);
    }

}
