package ru.cheb.intercity.bus.controller.parsers;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

public class BusStationSchedulerParserTest {



    @Test
    public void getScheduler() {

        String schedulerTable = BusStationSchedulerParser.getScheduler("/passengers/raspisanie/tsav/");

        Document doc = Jsoup.parse(schedulerTable);

        Elements elements = doc.getElementsByClass(ParserConstants.schedulerTableDivHtmlClass);
        System.out.println(elements.size());
        
    }

}