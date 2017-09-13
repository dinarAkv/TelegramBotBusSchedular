package ru.cheb.intercity.bus.parsers;


import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.cheb.intercity.bus.constants.ParserConstants;
import ru.cheb.intercity.bus.constants.PropertyConstants;
import ru.cheb.intercity.bus.helper.PropertiesHelper;

import java.io.IOException;

/**
 * Function parse description of scheduler table.
 */
public class BusStationSchedulerDescription {

    final static Logger logger = Logger.getLogger(BusStationSchedulerDescription.class);


    /**
     * Function return scheduler description html.
     * @param relationalUrl - relational url to particular station.
     * @return - html element of table description.
     */
    public static String getSchedulerDescription(String relationalUrl)
    {
        String hostUrl = PropertiesHelper.getPropByKeyInProperties( PropertyConstants.propertyFileName,
                                                                            PropertyConstants.propHostUrl);
        String fullUrl = hostUrl + relationalUrl;

        try {
            Document document = Jsoup.connect(fullUrl).get();
            String descriptionDivHtml = document.getElementsByClass(ParserConstants.schedulerDescriptionDivClass)
                                                                                                        .outerHtml();
            return descriptionDivHtml;
        } catch (IOException e) {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
        }

        throw new IllegalStateException();
    }

}
