package ru.cheb.intercity.bus.botainio;

import org.telegram.telegrambots.api.objects.Message;

public interface BotanTrack {




    void trackParameter( String userId, Object obj, String keyName);


    void trackParameter(Object obj, String keyName);
}
