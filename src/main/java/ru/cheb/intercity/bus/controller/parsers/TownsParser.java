package ru.cheb.intercity.bus.controller.parsers;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import ru.cheb.intercity.bus.controller.helper.PropertiesHelper;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * This class parse towns from bus station of Chuvash republic.
 */
public class TownsParser {

    final static Logger logger = Logger.getLogger(TownsParser.class);




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
    public static Map<String,String> getTownsSchedulerUrls() throws IOException {
        String hostUrl = PropertiesHelper.getPropByKeyInProperties(ParserConstants.propertyFileName,
                                                                   ParserConstants.propKeyUrlWithStations);

        Document doc = Jsoup.connect(hostUrl).get();
        Elements elements = doc.getElementsByClass(ParserConstants.divClassStationRef).select(htmlP);

        Map<String, String> descriptionAndUrl = new LinkedHashMap<>();

        elements.stream().forEach(element -> {
            String urlDescription = element.select(htmlA).attr(htmlHref);
            String relationalUrl = element.select(htmlA).select(htmlB).text();

            descriptionAndUrl.put(relationalUrl, urlDescription);
        });

        return descriptionAndUrl;
    }





}
