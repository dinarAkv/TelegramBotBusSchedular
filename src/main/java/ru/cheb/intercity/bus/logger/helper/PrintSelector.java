package ru.cheb.intercity.bus.logger.helper;

/**
 * Interface contain method to help print different type of variables.
 */
public interface PrintSelector {

    void printArray(Object array);

    void printList(Object list);

    void printMap(Object map);
}
