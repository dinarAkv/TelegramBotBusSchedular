package ru.cheb.intercity.bus.parsers;


/**
 * This parser get information about scheduler of concrete bus station.
 */
public interface BusStationSchedulerParser {


    /**
     * Function return html text of scheduler table.
     * @param relationalUrl - relational url of concrete bus station.
     * @return - html text of scheduler table. Return div element with scheduler table.
     */
    String getScheduler(String relationalUrl);

}
