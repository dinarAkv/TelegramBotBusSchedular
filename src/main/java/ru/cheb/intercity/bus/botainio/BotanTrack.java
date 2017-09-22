package ru.cheb.intercity.bus.botainio;

import org.telegram.telegrambots.api.objects.Message;

public interface BotanTrack {




    void trackParameter( String userId, Object obj, String keyName);


<<<<<<< HEAD

=======
    void trackParameter(Object obj, String keyName);
>>>>>>> 1d31c1b37e1377333ffd33cb3827281f57de48a9
}
