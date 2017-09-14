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
import ru.cheb.intercity.bus.parsers.BusStationsParser;
import ru.cheb.intercity.bus.parsers.BusStationsParserImpl;
import ru.cheb.intercity.bus.parsers.BusStationsParserImplTest;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BusStationBtnsGeneratorImplTest {

    final static Logger logger = Logger.getLogger(BusStationBtnsGeneratorImplTest.class);

    @Autowired
    BusStationBtnsGenerator busStationBtnsGenerator;

    @Autowired
    BusStationsParser stationsParser;

    @Test
    public void getKeyboardMarkupForBusStations() {

        List<List<InlineKeyboardButton>> rows = getListOfButtonsFromPrivateMethod();
        int trueSize = rows.size();

        try {
            InlineKeyboardMarkup keyboardMarkupForBusStations = busStationBtnsGenerator.getKeyboardMarkupForBusStations();
            int resultSize = keyboardMarkupForBusStations.getKeyboard().size();
            Assert.assertTrue(trueSize == resultSize);
        } catch (Exception e) {

        }
    }

    @Test
    public void getBusStationButtonsTest(){

        List<List<InlineKeyboardButton>> rows = getListOfButtonsFromPrivateMethod();

        rows.forEach(buttons->{
            buttons.forEach(button->{
                Assert.assertFalse(button.getText().isEmpty());
                Assert.assertTrue(button.getUrl().contains("/passengers/raspisanie/"));
            });
        });
    }


    /**
     * Function return list of {@link InlineKeyboardButton} from private method
     * for test purposes.
     * @return - list of {@link InlineKeyboardButton} objects.
     */
    private List<List<InlineKeyboardButton>> getListOfButtonsFromPrivateMethod()
    {
        String methodName = "getBusStationButtons";

        Method method = null;
        try {
            method = busStationBtnsGenerator.getClass().getDeclaredMethod(methodName);
            method.setAccessible(true);
            List<List<InlineKeyboardButton>> rows = (List<List<InlineKeyboardButton>>) method.invoke(busStationBtnsGenerator,null);

            return rows;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            logger.error(e);
        }

        throw new IllegalStateException();
    }


    @Test
    public void getRequestToSchedulerControllerUrlTest() throws Exception {
        String methodName = "getRequestToSchedulerControllerUrl";

        String mainUrlTrue = "http://localhost:" + ConnectionConstants.port + "/scheduler";


        Map<String, String> busStationSchedulerUrlsVsDescriptions = stationsParser.getBusStationsSchedulerUrls();
        busStationSchedulerUrlsVsDescriptions.entrySet().forEach(urlVsDescription->{
            Method method = null;
            try {
                method = busStationBtnsGenerator.getClass().getDeclaredMethod(methodName, Map.Entry.class);
                method.setAccessible(true);
                String requestUrl = (String) method.invoke(busStationBtnsGenerator,urlVsDescription);

                Assert.assertTrue(requestUrl.startsWith(mainUrlTrue));

                String withoutHttp = requestUrl.replaceAll("http://", "");
                String replaced2FToSlash = withoutHttp.replaceAll("%2F", "/");// replace %2F to /

                Assert.assertTrue(replaced2FToSlash.contains(urlVsDescription.getKey()));

            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });



    }

}