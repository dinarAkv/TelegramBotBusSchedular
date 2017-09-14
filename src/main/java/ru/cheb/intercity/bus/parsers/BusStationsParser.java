package ru.cheb.intercity.bus.parsers;


import java.io.IOException;
import java.util.Map;

/**
 * This class parses bus stations url and description in Chuvash republic.
 */
public interface BusStationsParser {

    /**
     * Function parse web-page with all intercity bus stations
     * of Chuvash republic.
     * @return - map, kye is relational url of concrete station
     *  and value is brief description of this station (where station locate).
     * @throws IOException
     */
    Map<String,String> getBusStationsSchedulerUrls()throws Exception ;

}
