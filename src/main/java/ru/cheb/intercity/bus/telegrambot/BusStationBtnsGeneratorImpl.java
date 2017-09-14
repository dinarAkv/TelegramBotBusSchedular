package ru.cheb.intercity.bus.telegrambot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.cheb.intercity.bus.constants.ControllerConstants;
import ru.cheb.intercity.bus.helper.UrlHelper;
import ru.cheb.intercity.bus.parsers.BusStationsParser;
import ru.cheb.intercity.bus.constants.EnvironmentVarConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Component
public class BusStationBtnsGeneratorImpl implements BusStationBtnsGenerator {

    @Autowired
    BusStationsParser stationsParser;

    @Autowired
    UrlHelper urlHelper;

    @Override
    public InlineKeyboardMarkup getKeyboardMarkupForBusStations() throws Exception {

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
    private List<List<InlineKeyboardButton>> getBusStationButtons() throws Exception {
        Map<String, String> busStationSchedulerUrlsVsDescriptions = stationsParser.getBusStationsSchedulerUrls();

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
    private String getRequestToSchedulerControllerUrl(Map.Entry<String, String> busStationUrlVsDescription)
    {
        String busStationHostUrl = System.getenv().get(EnvironmentVarConstants.busStationHostUrl);

        HashMap<String, String> queryParNameVsValue = new HashMap<>();
        queryParNameVsValue.put(ControllerConstants.relationalUrlParName, busStationUrlVsDescription.getKey());
        queryParNameVsValue.put(ControllerConstants.stationDescriptionPar, busStationUrlVsDescription.getValue());

        String schedulerRequestUrl = urlHelper.setQueryPar(busStationHostUrl, queryParNameVsValue);
        String urlWithout2F = schedulerRequestUrl.replaceAll("%2F", "/");

        return urlWithout2F;
    }


}
