package ru.cheb.intercity.bus.parsers;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.cheb.intercity.bus.constants.ParserConstants;
import ru.cheb.intercity.bus.helper.PropertiesHelper;
import ru.cheb.intercity.bus.helper.PropertiesHelperImpl;
import ru.cheb.intercity.bus.constants.PropertyConstants;
import ru.cheb.intercity.bus.logger.MethodLogger;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;


@Component
public class BusStationsParserImpl implements BusStationsParser {

    final static Logger logger = Logger.getLogger(BusStationsParserImpl.class);

    private static final String htmlP = "p";
    private static final String htmlA = "a";
    private static final String htmlB = "b";
    private static final String htmlHref = "href";

    @Autowired
    PropertiesHelper propertiesHelper;

    @MethodLogger
    @Override
    public Map<String,String> getBusStationsSchedulerUrls() throws IOException {
        String schedulerUrl = propertiesHelper.getPropByKeyInProperties(PropertyConstants.propertyFileName,
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
