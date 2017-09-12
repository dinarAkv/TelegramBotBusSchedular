package ru.cheb.intercity.bus.telegrambot;


import org.apache.http.client.utils.URIBuilder;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.cheb.intercity.bus.constants.ControllerConstants;
import ru.cheb.intercity.bus.helper.UrlHelper;
import ru.cheb.intercity.bus.parsers.BusStationsParser;
import ru.cheb.intercity.bus.constants.EnvironmentVarConstants;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * This class generate buttons for view in telegram bot. Where every button is
 * link to scheduler of respect intercity bus station.
 */
public class BusStationBtnsGenerator {

    /**
     * Function return markup with {@link InlineKeyboardButton} objects with
     * urls to bus stations scheduler.
     * @return - markup for rows of buttons with references to bus station urls.
     * @throws IOException
     */
    public static InlineKeyboardMarkup getKeyboardMarkupForBusStations() throws IOException {

        InlineKeyboardMarkup busStationsKeyboard = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> busStationButtons = getBusStationButtons();

        busStationsKeyboard.setKeyboard(busStationButtons);

        return busStationsKeyboard;
    }


    /**
     * Function return list of {@link InlineKeyboardButton} objects.
     * Each of this object filled with respect url and name of bus station.
     * Each button occupy entire row.
     * @return - list of {@link InlineKeyboardButton} for every bus station.
     * @throws IOException
     */
    private static List<List<InlineKeyboardButton>> getBusStationButtons() throws IOException {
        Map<String, String> busStationSchedulerUrlsVsDescriptions = BusStationsParser.getBusStationsSchedulerUrls();

        List<List<InlineKeyboardButton>> busStationButtons = new ArrayList<>(busStationSchedulerUrlsVsDescriptions.size());



        for (Map.Entry<String, String> busStationUrlVsDescription : busStationSchedulerUrlsVsDescriptions.entrySet())
        {
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText(busStationUrlVsDescription.getValue());

            String schedulerRequestUrl = getRequestToSchedulerControllerUrl(busStationUrlVsDescription);

            inlineKeyboardButton.setUrl(schedulerRequestUrl);

            List<InlineKeyboardButton> row = new ArrayList<>();
            row.add(inlineKeyboardButton);

            busStationButtons.add(row);
        }

        return busStationButtons;
    }


    /**
     * Function form request url for scheduler controller.
     * @param busStationUrlVsDescription - contains map relational url of scheduler vs description of this url
     *                                   (bus station name).
     * @return - full url request to scheduler controller.
     */
    private static String getRequestToSchedulerControllerUrl(Map.Entry<String, String> busStationUrlVsDescription)
    {
        String busStationHostUrl = System.getenv().get(EnvironmentVarConstants.busStationHostUrl);

        HashMap<String, String> queryParNameVsValue = new HashMap<>();
        queryParNameVsValue.put(ControllerConstants.relationalUrlParName, busStationUrlVsDescription.getKey());
        queryParNameVsValue.put(ControllerConstants.stationDescriptionPar, busStationUrlVsDescription.getValue());
        try {
            String schedulerRequestUrl = UrlHelper.setQueryPar(busStationHostUrl, queryParNameVsValue);

            String urlWithout2F = schedulerRequestUrl.replaceAll("%2F", "/");

            return urlWithout2F;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        throw new UnsupportedOperationException();
    }


}
