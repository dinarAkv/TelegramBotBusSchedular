package ru.cheb.intercity.bus.helper;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.util.Map;

@Component
public class UrlHelperImpl implements UrlHelper {


    /**
     * Function add query parameters to main url.
     * @param mainUrl - main url.
     * @param parNameVsValue - map query parameter to query value.
     * @return - main url with query parameters.
     * @throws URISyntaxException
     */
    @Override
    public String setQueryPar(String mainUrl, Map<String, String> parNameVsValue)
    {
        try {
            URIBuilder url = new URIBuilder(mainUrl);
            parNameVsValue.forEach((key,val)->{
                url.addParameter(key, val);
            });
            return url.toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        throw new  IllegalStateException();
    }

}
