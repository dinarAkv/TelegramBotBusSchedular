package ru.cheb.intercity.bus.helper;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URISyntaxException;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UrlHelperImplTest {

    @Autowired
    UrlHelper urlHelper;

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

        String resultUrl = urlHelper.setQueryPar(mainUrl,queryPar);
        Assert.assertTrue(resultUrl.equals(trueUrl));
    }

}