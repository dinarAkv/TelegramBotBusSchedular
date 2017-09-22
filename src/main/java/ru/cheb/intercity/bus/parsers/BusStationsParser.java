package ru.cheb.intercity.bus.parsers;


import java.io.IOException;
import java.util.Map;


public interface BusStationsParser {


    Map<String,String> getBusStationsSchedulerUrls()throws Exception ;

}
