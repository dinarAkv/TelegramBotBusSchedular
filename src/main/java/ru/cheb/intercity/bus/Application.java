package ru.cheb.intercity.bus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.cheb.intercity.bus.telegrambot.TelegramBotPolling;




@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Throwable {

        SpringApplication.run(Application.class, args);

//        TelegramBotPolling.registerBot();
    }




}
