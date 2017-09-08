package ru.cheb.intercity.bus.controller.telegrambot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.Map;


public class TelegramBotPolling extends TelegramLongPollingBot {

    private final String botUsername = "TELEGRAM_BOT_USERNAME";
    private final String botTokenEnv = "TELEGRAM_BOT_TOKEN";


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
        return env.get(botTokenEnv);
    }

    @Override
    public String getBotUsername() {
        Map<String, String> env = System.getenv();
        return env.get(botUsername);
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.getText().equals("hello"))
        {
            sendMsg(message, "hello");
        }
        else {
            sendMsg(message, "Uknown message.");
        }
    }

    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);

        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText("Button");



        try {
            execute(sendMessage);
            
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
