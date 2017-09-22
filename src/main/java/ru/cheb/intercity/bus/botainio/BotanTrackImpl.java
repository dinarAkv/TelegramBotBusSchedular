package ru.cheb.intercity.bus.botainio;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.Message;
import ru.cheb.intercity.bus.constants.BotanIOConstants;

import java.io.IOException;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutionException;

/**
 * This class realize tracking function of botanio.
 */
@Component
public class BotanTrackImpl implements BotanTrack{

    // last chatId.
    String chatId;

    public void trackParameter(String chatId, Object obj, String keyName) {
        try (CloseableHttpAsyncClient client = HttpAsyncClients.createDefault()) {

<<<<<<< HEAD
            String string = new String("value:" + obj.toString());

=======
>>>>>>> 1d31c1b37e1377333ffd33cb3827281f57de48a9
            client.start();
            Botan botan = new Botan(client, new ObjectMapper());
            botan.track(BotanIOConstants.yandexAppMetricaToken, chatId, obj, keyName).get();

        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

<<<<<<< HEAD
=======

    @Override
    public void trackParameter(Object obj, String keyName) {
        if (chatId != null){

        }
        else {
            throw new IllegalStateException();
        }
    }
>>>>>>> 1d31c1b37e1377333ffd33cb3827281f57de48a9
}
