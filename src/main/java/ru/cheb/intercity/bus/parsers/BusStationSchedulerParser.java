package ru.cheb.intercity.bus.parsers;


import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.cheb.intercity.bus.helper.PropertiesHelper;
import ru.cheb.intercity.bus.constants.PropertyConstants;


import java.io.IOException;

/**
 * This parser get information about scheduler of concrete bus station.
 */
public class BusStationSchedulerParser {

    final static Logger logger = Logger.getLogger(BusStationSchedulerParser.class);


    private static final String htmlTable = "table";


    /**
     * Function return html text of scheduler table.
     * @param relationalUrl - relational url of concrete bus station.
     * @return - html text of scheduler table. Return div element with scheduler table.
     */
    public static String getScheduler(String relationalUrl)
    {
       String hostUrl = PropertiesHelper.getPropByKeyInProperties( PropertyConstants.propertyFileName,
                                                                   PropertyConstants.propHostUrl);
       String fullUrl = hostUrl + relationalUrl;

        try {
            Document doc = Jsoup.connect(fullUrl).get();
            return doc.getElementsByClass(ParserConstants.schedulerTableDivHtmlClass).outerHtml();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        throw new IllegalStateException();
    }





}
