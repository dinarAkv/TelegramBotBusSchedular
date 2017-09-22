package ru.cheb.intercity.bus.telegrambot;


import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.cheb.intercity.bus.constants.ConnectionConstants;
import ru.cheb.intercity.bus.constants.ControllerConstants;
import ru.cheb.intercity.bus.parsers.BusStationsParser;
import ru.cheb.intercity.bus.parsers.BusStationsParserImpl;
import ru.cheb.intercity.bus.parsers.BusStationsParserImplTest;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BusStationBtnsGeneratorImplTest {

    final static Logger logger = Logger.getLogger(BusStationBtnsGeneratorImplTest.class);

    @Autowired
    BusStationBtnsGenerator busStationBtnsGenerator;

    @Autowired
    BusStationsParser stationsParser;

    private long fakeChatId = 777L;

    @Test
    public void getKeyboardMarkupForBusStations() {

        try {
            List<List<InlineKeyboardButton>> rows = busStationBtnsGenerator.getKeyboardMarkupForBusStations(fakeChatId).getKeyboard();

            checkStationButtonsTest(rows);

            int trueSize = rows.size();

            InlineKeyboardMarkup keyboardMarkupForBusStations = busStationBtnsGenerator.getKeyboardMarkupForBusStations(fakeChatId);
            int resultSize = keyboardMarkupForBusStations.getKeyboard().size();
            Assert.assertTrue(trueSize == resultSize);
        } catch (Exception e) {

        }
    }

    /**
     * Check text and url of every {@link InlineKeyboardButton}.
     * @param rows - every row contain list of buttons in this line.
     */
    private void checkStationButtonsTest(List<List<InlineKeyboardButton>> rows){

        try {
            rows = busStationBtnsGenerator.getKeyboardMarkupForBusStations(fakeChatId).getKeyboard();
        } catch (Exception e) {
            e.printStackTrace();
        }

        rows.forEach(buttons->{
            buttons.forEach(button->{
                checkChatIdUrlPar(button.getUrl());
                checkDescriptionUrlPar(button.getUrl());
                checkRelationalUrlPar(button.getUrl());

                Assert.assertFalse(button.getText().isEmpty());
                Assert.assertTrue(button.getUrl().contains("/passengers/raspisanie/"));
            });
        });
    }


    /**
     * Check if button url contain query parameter {@link ControllerConstants#chatIdPar}.
     * @param url - url to bus station scheduler web-page.
     */
    private void checkChatIdUrlPar(String url){

        Pattern p = Pattern.compile("^.+" + ControllerConstants.chatIdPar + "=.+$");
        Matcher matcher = p.matcher(url);

        Assert.assertTrue(matcher.matches());
    }

    /**
     * Check if button url contain query parameter {@link ControllerConstants#stationDescriptionPar}.
     * @param url - url to bus station scheduler web-page.
     */
    private void checkDescriptionUrlPar(String url){
        Pattern p = Pattern.compile("^.+" + ControllerConstants.stationDescriptionPar + "=.+$");
        Matcher matcher = p.matcher(url);

        Assert.assertTrue(matcher.matches());
    }

    /**
     * Check if button url contain query parameter {@link ControllerConstants#relationalUrlParName}.
     * @param url - url to bus station scheduler web-page.
     */
    private void checkRelationalUrlPar(String url){
        Pattern p = Pattern.compile("^.+" + ControllerConstants.relationalUrlParName + "=.+$");
        Matcher matcher = p.matcher(url);

        Assert.assertTrue(matcher.matches());
    }

}