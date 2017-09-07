package ru.cheb.intercity.bus.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.cheb.intercity.bus.controller.telegrambot.TelegramBotPolling;


// Token: 377263517:AAH5mLjwUv0-ROXzLRkiml28V9DXf8bqW6E

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Throwable {

        SpringApplication.run(Application.class, args);

        TelegramBotPolling.registerBot();
    }




}
