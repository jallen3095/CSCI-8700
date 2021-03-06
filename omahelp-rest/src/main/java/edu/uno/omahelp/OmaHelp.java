package edu.uno.omahelp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("edu.uno.omahelp")
public class OmaHelp {
    public static void main(String[] args) {
        SpringApplication.run(OmaHelp.class, args);
    }
}
