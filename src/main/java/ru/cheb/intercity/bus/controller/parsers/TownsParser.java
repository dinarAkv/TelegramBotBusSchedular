package ru.cheb.intercity.bus.controller.parsers;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;


/**
 * This class parse towns from bus station of Chuvash republic.
 */
public class TownsParser {

    final static Logger logger = Logger.getLogger(TownsParser.class);

    private static String propertyUrlWithStations = "avtoVasUrlWithStations";
    private static String propertyFileName = "./src/main/resources/params.properties";


    /**
     * Function parse web-page with all intercity bus stations
     * of Chuvash republic.
     * @return - map, kye is relational url of concrete station
     *  and value is brief description of this station (where station locate).
     * @throws IOException
     */
    public static Map<String,String> getTownsSchedulerUrls() throws IOException {
        String hostUrl = getStationsWebSiteUrl(propertyFileName, propertyUrlWithStations);

        Document doc = Jsoup.connect(hostUrl).get();
        Elements elements = doc.getElementsByClass("news-list").select("p");

        Map<String, String> descriptionAndUrl = new LinkedHashMap<>();

        elements.stream().forEach(element -> {
            String urlDescription = element.select("a").attr("href");
            String relationalUrl = element.select("a").select("b").text();

            descriptionAndUrl.put(relationalUrl, urlDescription);
        });

        return descriptionAndUrl;
    }


    /**
     * Function return website url of bust stations in Chuvash republic.
     * @param fileName - name of "*.properties" file.
     * @param propertyKey - key string of particular property.
     * @return - host website url.
     */
    protected static String getStationsWebSiteUrl(String fileName, String propertyKey)
    {
        Properties properties = new Properties();

        try(InputStream input = new FileInputStream(fileName)) {
            properties.load(input);
            return properties.getProperty(propertyKey);
        }
        catch (IOException ex)
        {
            logger.error(ex.getMessage());
        }

        throw new IllegalStateException();
    }


}
