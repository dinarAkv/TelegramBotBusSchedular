package ru.cheb.intercity.bus.telegrambot;


import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.IOException;

/**
 * This class generate buttons for view in telegram bot. Where every button is
 * link to scheduler of respect intercity bus station.
 */
public interface BusStationBtnsGenerator {

    /**
     * Function return markup with {@link InlineKeyboardButton} objects with
     * urls to bus stations scheduler.
     * @return - markup for rows of buttons with references to bus station urls.
     * @throws IOException
     */
    InlineKeyboardMarkup getKeyboardMarkupForBusStations() throws Exception;

}
