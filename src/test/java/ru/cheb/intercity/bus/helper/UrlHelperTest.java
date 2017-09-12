package ru.cheb.intercity.bus.helper;


import org.junit.Assert;
import org.junit.Test;

import java.net.URISyntaxException;
import java.util.HashMap;

public class UrlHelperTest {

    @Test
    public void setQueryPar() {
        String mainUrl = new String("http://test.org");
        HashMap<String, String> queryPar = new HashMap<>();

        String key1 = "key1";
        String key2 = "key2";
        String val1 = "val1";
        String val2 = "val2";

        queryPar.put(key1, val1);
        queryPar.put(key2, val2);

        String trueUrl = "http://test.org?key1=val1&key2=val2";

        try {
            String resultUrl = UrlHelper.setQueryPar(mainUrl,queryPar);
            Assert.assertTrue(resultUrl.equals(trueUrl));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}