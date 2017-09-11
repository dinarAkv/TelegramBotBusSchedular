package ru.cheb.intercity.bus.helper;

import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;
import java.util.Map;

/**
 * This class contains methods that help to process different urls.
 */
public class UrlHelper {


    /**
     * Function add query parameters to main url.
     * @param mainUrl - main url.
     * @param parNameVsValue - map query parameter to query value.
     * @return - main url with query parameters.
     * @throws URISyntaxException
     */
    public static String setQueryPar(String mainUrl, Map<String, String> parNameVsValue)
                                                                throws URISyntaxException {

        URIBuilder url = new URIBuilder(mainUrl);
        parNameVsValue.forEach((key,val)->{
            url.addParameter(key, val);
        });

        return url.toString();
    }

}
