package ru.cheb.intercity.bus.telegrambot;


import org.junit.Assert;
import org.junit.Test;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.cheb.intercity.bus.constants.ConnectionConstants;
import ru.cheb.intercity.bus.parsers.BusStationsParser;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class BusStationBtnsGeneratorTest {

    @Test
    public void getKeyboardMarkupForBusStations() {

        List<List<InlineKeyboardButton>> rows = getListOfButtonsFromPrivateMethod();
        int trueSize = rows.size();

        try {
            InlineKeyboardMarkup keyboardMarkupForBusStations = BusStationBtnsGenerator.getKeyboardMarkupForBusStations();
            int resultSize = keyboardMarkupForBusStations.getKeyboard().size();
            Assert.assertTrue(trueSize == resultSize);
        } catch (IOException e) {
            e.printStackTrace();
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

        BusStationBtnsGenerator busStationBtnsGenerator = new BusStationBtnsGenerator();

        Method method = null;
        try {
            method = busStationBtnsGenerator.getClass().getDeclaredMethod(methodName);
            method.setAccessible(true);
            List<List<InlineKeyboardButton>> rows = (List<List<InlineKeyboardButton>>) method.invoke(null);

            return rows;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        throw new IllegalStateException();
    }


    @Test
    public void getRequestToSchedulerControllerUrlTest() throws IOException {
        String methodName = "getRequestToSchedulerControllerUrl";

        String mainUrlTrue = "http://localhost:" + ConnectionConstants.port + "/scheduler";

        BusStationBtnsGenerator busStationBtnsGenerator = new BusStationBtnsGenerator();
        Map<String, String> busStationSchedulerUrlsVsDescriptions = BusStationsParser.getBusStationsSchedulerUrls();
        busStationSchedulerUrlsVsDescriptions.entrySet().forEach(urlVsDescription->{
            Method method = null;
            try {
                method = busStationBtnsGenerator.getClass().getDeclaredMethod(methodName, Map.Entry.class);
                method.setAccessible(true);
                String requestUrl = (String) method.invoke(null,urlVsDescription);

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