package com.example.librarymanagementsystemnew;

import com.example.librarymanagementsystemnew.controller.BookDetailsController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryManagementSystemNewApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementSystemNewApplication.class, args);
    }

    // Demo: call the controller directly on startup and print its response
//    @Bean
//    public CommandLineRunner demo(BookDetailsController bookDetailsController) {
//        return args -> {
//            String result = bookDetailsController.sayBookDetails();
//            System.out.println("[Startup demo] BookDetailsController response: " + result);
//        };
//    }

}
