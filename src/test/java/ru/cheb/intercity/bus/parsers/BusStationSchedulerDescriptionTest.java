package ru.cheb.intercity.bus.parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class BusStationSchedulerDescriptionTest {

    @Test
    public void getSchedulerDescriptionTest()
    {
        String schedulerTable = BusStationSchedulerDescription
                                        .getSchedulerDescription("/passengers/raspisanie/tsav/");
        Document doc = Jsoup.parse(schedulerTable);

        int numberOfTableHtml = 1;

        Elements elements = doc.getElementsByClass(ParserConstants.schedulerDescriptionDivClass);
        assertTrue(numberOfTableHtml == elements.size());
    }


}
