package ru.cheb.intercity.bus.telegrambot;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import ru.cheb.intercity.bus.constants.EnvironmentVarConstants;
import ru.cheb.intercity.bus.parsers.BusStationSchedulerParser;

import java.io.IOException;
import java.util.Map;


public class TelegramBotPolling extends TelegramLongPollingBot {


    final static Logger logger = Logger.getLogger(TelegramBotPolling.class);

    public static void registerBot()
    {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new TelegramBotPolling());
        } catch (TelegramApiException e) {
            e.printStackTrace();
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

        System.out.println("Start message: " + message.getText());

        if (message != null && message.getText().equals("/START"))
        {
            sendMsg(message, "Пожалуйста, выберите интересующее вас расписание автовокзала:");
        }
        else {
            sendMsg(message, "Uknown message.");
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
            inlineKeyboardMarkup = BusStationBtnsGenerator.getKeyboardMarkupForBusStations();
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
            execute(sendMessage);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
