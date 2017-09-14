package ru.cheb.intercity.bus.parsers;


import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.cheb.intercity.bus.constants.ParserConstants;
import ru.cheb.intercity.bus.constants.PropertyConstants;
import ru.cheb.intercity.bus.helper.PropertiesHelper;
import ru.cheb.intercity.bus.helper.PropertiesHelperImpl;

import java.io.IOException;

@Component
public class BusStationSchedulerDescriptionParserImpl implements BusStationSchedulerDescriptionParser {

    final static Logger logger = Logger.getLogger(BusStationSchedulerDescriptionParserImpl.class);

    @Autowired
    PropertiesHelper propertiesHelper;

    @Override
    public String getSchedulerDescription(String relationalUrl)
    {
        String hostUrl = propertiesHelper.getPropByKeyInProperties( PropertyConstants.propertyFileName,
                                                                            PropertyConstants.propHostUrl);
        String fullUrl = hostUrl + relationalUrl;

        try {
            Document document = Jsoup.connect(fullUrl).get();
            String descriptionDivHtml = document.getElementsByClass(ParserConstants.schedulerDescriptionDivClass)
                                                                                                        .outerHtml();
            return descriptionDivHtml;
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        throw new IllegalStateException();
    }

}
