package com.lardi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
public class PhonebookApplication {

    public static void main(String[] args) {
       SpringApplication.run(PhonebookApplication.class, args);
//        Properties prop = new Properties();
//        InputStream input = null;
//        try {
//            input = new FileInputStream(args[0].replaceAll("lardi.conf=/",""));
//            prop.load(input);
//            prop.getProperty("spring.mvc.view.prefix");
//            prop.getProperty("spring.mvc.view.suffix");
//            prop.getProperty("spring.datasource.url");
//            prop.getProperty("spring.datasource.username");
//            prop.getProperty("spring.datasource.password");
//            prop.getProperty("spring.datasource.driver-class-name");
//            prop.getProperty("spring.jpa.database-platform");
//            prop.getProperty("spring.jpa.hibernate.ddl-auto");
//            prop.getProperty("spring.jpa.properties.hibernate.current_session_context_class");
//            prop.getProperty("spring.jpa.hibernate.naming-strategy");
//            prop.getProperty("server.port");
//            prop.getProperty("information.repository");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        } finally {
//            if (input != null) {
//                try {
//                    input.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//         new SpringApplicationBuilder()
//                .properties(prop)
//                .logStartupInfo(true)
//                .headless(true)
//                .application()
//                .run(args);
    }
}
