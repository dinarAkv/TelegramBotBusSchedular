package ru.cheb.intercity.bus.telegrambot;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.generics.LongPollingBot;
import ru.cheb.intercity.bus.constants.EnvironmentVarConstants;
import ru.cheb.intercity.bus.constants.TelegramBotConstants;


import java.io.IOException;
import java.util.Map;

@Repository
public class TelegramBotPolling extends TelegramLongPollingBot {


    final static Logger logger = Logger.getLogger(TelegramBotPolling.class);

    @Autowired
    BusStationBtnsGenerator busStationBtnsGenerator;

    static {
        ApiContextInitializer.init();
    }

    public static void registerBot()
    {

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new TelegramBotPolling());
        } catch (TelegramApiException e) {
            logger.error(e);
        }
    }


    @Override
    public String getBotToken() {
        Map<String, String> env = System.getenv();
        return env.get(EnvironmentVarConstants.botTokenEnv);
    }

    @Override
    public String getBotUsername() {
        Map<String, String> env = System.getenv();
        return env.get(EnvironmentVarConstants.botUsername);
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();

        if (message != null && message.getText().equals(TelegramBotConstants.startCmd))
        {
            sendMsg(message, TelegramBotConstants.welcomeMessage);
        }
        else {
            sendMsg(message, TelegramBotConstants.uknownMes);
        }
    }

    /**
     * Function send message to telegram bot.
     * @param message - message object to edit.
     * @param text -
     */
    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(false);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);




        InlineKeyboardMarkup inlineKeyboardMarkup = null;
        try {
            inlineKeyboardMarkup = busStationBtnsGenerator.getKeyboardMarkupForBusStations();
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
            execute(sendMessage);
        } catch (Exception e) {
            logger.error(e);
        }

    }
}
