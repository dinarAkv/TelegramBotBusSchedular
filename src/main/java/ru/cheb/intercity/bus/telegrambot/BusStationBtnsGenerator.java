package ru.cheb.intercity.bus.telegrambot;


import org.apache.http.client.utils.URIBuilder;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.cheb.intercity.bus.parsers.BusStationsParser;
import ru.cheb.intercity.bus.constants.EnvironmentVarConstants;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
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

        String thisServerHostUrl = EnvironmentVarConstants.botTokenEnv;



        for (Map.Entry<String, String> busStationUrlVsDescription : busStationSchedulerUrlsVsDescriptions.entrySet())
        {
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText(busStationUrlVsDescription.getKey());
            inlineKeyboardButton.setUrl(EnvironmentVarConstants.busStationHostUrl + busStationUrlVsDescription.getValue());

            List<InlineKeyboardButton> row = new ArrayList<>();
            row.add(inlineKeyboardButton);

            busStationButtons.add(row);
        }

        return busStationButtons;
    }





}
