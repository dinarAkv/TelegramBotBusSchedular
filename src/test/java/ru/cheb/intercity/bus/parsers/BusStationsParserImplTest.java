package ru.cheb.intercity.bus.parsers;






import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BusStationsParserImplTest {


    final static Logger logger = Logger.getLogger(BusStationsParserImplTest.class);

    @Autowired
    BusStationsParser stationsParser;




    @Test
    public void getTownsSchedulerUrls() {
        try {
            Map<String, String> townsSchedulerUrls = stationsParser.getBusStationsSchedulerUrls();

            String rightBeginOfUrl = "/passengers/";

            townsSchedulerUrls.entrySet().forEach(entry->{
                String url = entry.getKey();

                assert url.startsWith(rightBeginOfUrl);
            });

        } catch (Exception e) {
            logger.error(e);
        }
    }





}