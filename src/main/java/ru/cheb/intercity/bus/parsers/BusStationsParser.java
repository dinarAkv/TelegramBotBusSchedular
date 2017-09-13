package ru.cheb.intercity.bus.parsers;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import ru.cheb.intercity.bus.constants.ParserConstants;
import ru.cheb.intercity.bus.helper.PropertiesHelper;
import ru.cheb.intercity.bus.constants.PropertyConstants;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * This class parses bus stations url and description in Chuvash republic.
 */
public class BusStationsParser {

    final static Logger logger = Logger.getLogger(BusStationsParser.class);




    private static final String htmlP = "p";
    private static final String htmlA = "a";
    private static final String htmlB = "b";
    private static final String htmlHref = "href";



    /**
     * Function parse web-page with all intercity bus stations
     * of Chuvash republic.
     * @return - map, kye is relational url of concrete station
     *  and value is brief description of this station (where station locate).
     * @throws IOException
     */
    public static Map<String,String> getBusStationsSchedulerUrls() throws IOException {
        String schedulerUrl = PropertiesHelper.getPropByKeyInProperties(PropertyConstants.propertyFileName,
                                                                   PropertyConstants.propKeyUrlWithStations);

        Document doc = Jsoup.connect(schedulerUrl).get();
        Elements elements = doc.getElementsByClass(ParserConstants.divClassStationRef).select(htmlP);

        Map<String, String> descriptionAndUrl = new LinkedHashMap<>();

        elements.stream().forEach(element -> {
            String urlDescription = element.select(htmlA).select(htmlB).text();
            String relationalUrl = element.select(htmlA).attr(htmlHref);

            descriptionAndUrl.put(relationalUrl, urlDescription);
        });

        return descriptionAndUrl;
    }





}
