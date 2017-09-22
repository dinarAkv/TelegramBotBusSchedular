package ru.cheb.intercity.bus.botainio;


/**
 * Class to convert description of bus station to json object.
 */
public class BusStation {


    private String name;


    public BusStation(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
