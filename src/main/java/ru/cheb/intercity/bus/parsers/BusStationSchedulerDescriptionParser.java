package ru.cheb.intercity.bus.parsers;


public interface BusStationSchedulerDescriptionParser {

    /**
     * Function return scheduler description html.
     * @param relationalUrl - relational url to particular station.
     * @return - html element of table description.
     */
    String getSchedulerDescription(String relationalUrl);
}
