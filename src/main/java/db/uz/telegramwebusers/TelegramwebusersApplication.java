package db.uz.telegramwebusers;


import db.uz.telegramwebusers.Controller.Bot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@SpringBootApplication
public class TelegramwebusersApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelegramwebusersApplication.class, args);

        System.out.println("Start bot");
        ApiContextInitializer.init();



    }

}
