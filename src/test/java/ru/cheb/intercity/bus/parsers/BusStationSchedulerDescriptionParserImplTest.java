package ru.cheb.intercity.bus.parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.cheb.intercity.bus.constants.ParserConstants;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BusStationSchedulerDescriptionParserImplTest {

    @Autowired
    BusStationSchedulerDescriptionParser descriptionParser;

    @Test
    public void getSchedulerDescriptionTest()
    {
        String schedulerTable = descriptionParser.getSchedulerDescription("/passengers/raspisanie/tsav/");
        Document doc = Jsoup.parse(schedulerTable);

        int numberOfTableHtml = 1;

        Elements elements = doc.getElementsByClass(ParserConstants.schedulerDescriptionDivClass);
        assertTrue(numberOfTableHtml == elements.size());
    }


}
