package ru.cheb.intercity.bus.telegrambot;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import ru.cheb.intercity.bus.botainio.BotanTrack;
import ru.cheb.intercity.bus.constants.BotanIOConstants;
import ru.cheb.intercity.bus.constants.EnvironmentVarConstants;
import ru.cheb.intercity.bus.constants.TelegramBotConstants;
import ru.cheb.intercity.bus.helper.EnvVarHelper;


import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class TelegramBotPolling extends TelegramLongPollingBot  {


    final static Logger logger = Logger.getLogger(TelegramBotPolling.class);

    @Autowired
    BusStationBtnsGenerator busStationBtnsGenerator;

    @Autowired
    EnvVarHelper envVarHelper;

    @Autowired
    BotanTrack botanTrack;

    static  {
        ApiContextInitializer.init();
    }

    @PostConstruct
    public void registerBot(){
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiException e) {
            logger.error(e);
        }
    }


    @Override
    public String getBotToken() {
        return envVarHelper.getEnvVar(EnvironmentVarConstants.botTokenEnv);
    }

    @Override
    public String getBotUsername() {
        return  envVarHelper.getEnvVar(EnvironmentVarConstants.botUsername);
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

        botanTrack.trackParameter(message.getChatId().toString(), message, BotanIOConstants.messagesFromUser);

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(false);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);



        InlineKeyboardMarkup inlineKeyboardMarkup = null;
        try {
            inlineKeyboardMarkup = busStationBtnsGenerator
                                        .getKeyboardMarkupForBusStations(message.getChatId());
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
            execute(sendMessage);
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
