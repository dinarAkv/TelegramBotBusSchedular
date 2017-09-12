package ru.cheb.intercity.bus.parsers;






import org.junit.Test;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import static org.junit.Assert.assertTrue;


public class BusStationsParserTest {







    @Test
    public void getTownsSchedulerUrls() {
        try {
            Map<String, String> townsSchedulerUrls = BusStationsParser.getBusStationsSchedulerUrls();

            String rightBeginOfUrl = "/passengers/";

            townsSchedulerUrls.entrySet().forEach(entry->{
                String url = entry.getValue();

                assert url.startsWith(rightBeginOfUrl);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}