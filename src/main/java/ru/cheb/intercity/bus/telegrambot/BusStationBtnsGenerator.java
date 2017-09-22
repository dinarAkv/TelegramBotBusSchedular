package ru.cheb.intercity.bus.telegrambot;


import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.IOException;


public interface BusStationBtnsGenerator {


    InlineKeyboardMarkup getKeyboardMarkupForBusStations(Long chatId) throws Exception;

}
