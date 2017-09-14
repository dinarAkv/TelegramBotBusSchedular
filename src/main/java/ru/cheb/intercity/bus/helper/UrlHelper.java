package ru.cheb.intercity.bus.helper;


import java.util.Map;

/**
 * This interface contains methods that help to process different urls.
 */
public interface UrlHelper {

    String setQueryPar(String mainUrl, Map<String, String> parNameVsValue);
}
