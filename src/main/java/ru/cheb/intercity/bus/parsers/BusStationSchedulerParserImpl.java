package ru.cheb.intercity.bus.parsers;


import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.cheb.intercity.bus.constants.ParserConstants;
import ru.cheb.intercity.bus.helper.PropertiesHelper;
import ru.cheb.intercity.bus.helper.PropertiesHelperImpl;
import ru.cheb.intercity.bus.constants.PropertyConstants;


import java.io.IOException;

@Component
public class BusStationSchedulerParserImpl implements BusStationSchedulerParser {

    final static Logger logger = Logger.getLogger(BusStationSchedulerParserImpl.class);

    @Autowired
    PropertiesHelper propertiesHelper;

    @Override
    public String getScheduler(String relationalUrl)
    {
       String hostUrl = propertiesHelper.getPropByKeyInProperties( PropertyConstants.propertyFileName,
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
