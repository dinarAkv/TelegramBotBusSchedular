package ru.cheb.intercity.bus.telegrambot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.cheb.intercity.bus.constants.ControllerConstants;
import ru.cheb.intercity.bus.helper.EnvVarHelper;
import ru.cheb.intercity.bus.helper.UrlHelper;
import ru.cheb.intercity.bus.logger.MethodLogger;
import ru.cheb.intercity.bus.parsers.BusStationsParser;
import ru.cheb.intercity.bus.constants.EnvironmentVarConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * This class generate buttons for view in telegram bot. Where every button is
 * link to scheduler of respect intercity bus station.
 */
@Component
public class BusStationBtnsGeneratorImpl implements BusStationBtnsGenerator {

    @Autowired
    BusStationsParser stationsParser;

    @Autowired
    UrlHelper urlHelper;

    @Autowired
    EnvVarHelper envVarHelper;



    /**
     * Function return markup with {@link InlineKeyboardButton} objects with
     * urls to bus stations scheduler.
     * @param chatId - user id.
     * @return - markup for rows of buttons with references to bus station urls.
     * @throws IOException
     */
    @MethodLogger
    @Override
    public InlineKeyboardMarkup getKeyboardMarkupForBusStations(Long chatId) throws Exception {

        InlineKeyboardMarkup busStationsKeyboard = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> busStationButtons = getBusStationButtons(chatId);

        busStationsKeyboard.setKeyboard(busStationButtons);

        return busStationsKeyboard;
    }


    /**
     * Function return list of {@link InlineKeyboardButton} objects.
     * Each of this object filled with respect url and name of bus station.
     * Each button occupy entire row.
     * @param chatId - user id.
     * @return - list of {@link InlineKeyboardButton} for every bus station.
     * @throws IOException
     */
    private List<List<InlineKeyboardButton>> getBusStationButtons(Long chatId) throws Exception {
        Map<String, String> busStationSchedulerUrlsVsDescriptions = stationsParser.getBusStationsSchedulerUrls();

        List<List<InlineKeyboardButton>> busStationButtons = new ArrayList<>(busStationSchedulerUrlsVsDescriptions.size());



        for (Map.Entry<String, String> busStationUrlVsDescription : busStationSchedulerUrlsVsDescriptions.entrySet())
        {
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText(busStationUrlVsDescription.getValue());

            String schedulerRequestUrl = getRequestToSchedulerControllerUrl(busStationUrlVsDescription, chatId);

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
     * @param chatId - user id.
     * @return - full url request to scheduler controller.
     */
    private String getRequestToSchedulerControllerUrl(Map.Entry<String, String> busStationUrlVsDescription, Long chatId)
    {
        String busStationHostUrl = envVarHelper.getEnvVar(EnvironmentVarConstants.busStationHostUrl);

        String schedulerRequestUrl = addQueryParameters(busStationHostUrl, busStationUrlVsDescription, chatId);

        String urlWithout2F = schedulerRequestUrl.replaceAll("%2F", "/");

        return urlWithout2F;
    }


    /**
     * Function form query parameters for url.
     * @param hostUrl - host url of this application server.
     * @param busStationUrlVsDescription - station name.
     * @param chatId - user id.
     * @return - url with query parameters.
     */
    private String addQueryParameters(String hostUrl, Map.Entry<String, String> busStationUrlVsDescription, Long chatId){

        HashMap<String, String> queryParNameVsValue = new HashMap<>();

        // Add relational url of station.
        queryParNameVsValue.put(ControllerConstants.relationalUrlParName, busStationUrlVsDescription.getKey());
        // Add description of station.
        queryParNameVsValue.put(ControllerConstants.stationDescriptionPar, busStationUrlVsDescription.getValue());
        // Add userId.
        queryParNameVsValue.put(ControllerConstants.chatIdPar, Long.toString(chatId));

        String schedulerRequestUrl = urlHelper.setQueryPar(hostUrl, queryParNameVsValue);

        return schedulerRequestUrl;
    }

}
