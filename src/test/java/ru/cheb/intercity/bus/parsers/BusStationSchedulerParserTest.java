package ru.cheb.intercity.bus.parsers;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import ru.cheb.intercity.bus.constants.ParserConstants;

import static junit.framework.TestCase.assertTrue;

public class BusStationSchedulerParserTest {



    @Test
    public void getScheduler() {

        String schedulerTable = BusStationSchedulerParser.getScheduler("/passengers/raspisanie/tsav/");
        Document doc = Jsoup.parse(schedulerTable);

        int numberOfTableHtml = 1;

        Elements elements = doc.getElementsByClass(ParserConstants.schedulerTableDivHtmlClass);
        assertTrue(numberOfTableHtml == elements.size());
        
    }

}