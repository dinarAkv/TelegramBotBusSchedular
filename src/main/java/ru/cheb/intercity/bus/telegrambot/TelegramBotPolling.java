package ru.cheb.intercity.bus.telegrambot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import ru.cheb.intercity.bus.constants.EnvironmentVarConstants;

import java.io.IOException;
import java.util.Map;


public class TelegramBotPolling extends TelegramLongPollingBot {




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
        if (message != null && message.getText().equals("hello"))
        {
            sendMsg(message, "Пожалуйста, выберите интересующее вас расписание автовокзала:");
        }
        else {
            sendMsg(message, "Uknown message.");
        }
    }

    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(false);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);



//        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
//        inlineKeyboardButton.setText("Button");
//        inlineKeyboardButton.setUrl("https://core.telegram.org");
//
//        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
//        inlineKeyboardButton2.setText("Button2");
//        inlineKeyboardButton2.setUrl("https://core.telegram.org");


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


//        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
//        List<InlineKeyboardButton> rowInline = new ArrayList<>();
//
//        rowInline.add(inlineKeyboardButton);
//        rowInline.add(inlineKeyboardButton2);
//        rowsInline.add(rowInline);
//
//
//        inlineKeyboardMarkup.setKeyboard(rowsInline);





    }
}
