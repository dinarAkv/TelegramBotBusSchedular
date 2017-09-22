package ru.cheb.intercity.bus.logger.helper;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Class contain method to help print different type of variables.
 */
@Component
public class PrintSelectorImpl implements PrintSelector {

    /**
     * Function print each element of array in separate row.
     * @param arrayObj - array object.
     */
    @Override
    public void printArray(Object arrayObj) {
        System.out.println();
        Object[] objArr = (Object[]) arrayObj;
        Arrays.stream(objArr).forEach(System.out::println);
    }

    /**
     * Function print each element of list in separate row.
     * @param listObj - list object.
     */
    @Override
    public void printList(Object listObj) {
        System.out.println();
        List list = (List) listObj;
        list.forEach(System.out::println);
    }

    /**
     * Function print each entry of map in separate row.
     * @param mapObj - map object.
     */
    @Override
    public void printMap(Object mapObj) {
        System.out.println();
        Map map = (Map) mapObj;
        map.entrySet().forEach(System.out::println);
    }
}
