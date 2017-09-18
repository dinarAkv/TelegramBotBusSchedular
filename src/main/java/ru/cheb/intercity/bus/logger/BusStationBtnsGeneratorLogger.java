package ru.cheb.intercity.bus.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.cheb.intercity.bus.logger.helper.CommonLogHandler;
import ru.cheb.intercity.bus.parsers.BusStationsParser;
import ru.cheb.intercity.bus.telegrambot.BusStationBtnsGenerator;

/**
 * Function realize logging of methods of class {@link ru.cheb.intercity.bus.telegrambot.BusStationBtnsGeneratorImpl}.
 */
@Aspect
@Component
public class BusStationBtnsGeneratorLogger {

    @Autowired
    CommonLogHandler commonLogHandler;

    /**
     * Function make pointcut in method {@link BusStationBtnsGenerator#getKeyboardMarkupForBusStations()}.
     * @param joinPoint - contain method context;
     * @return - methods return value.
     */
    @Around("execution(* ru.cheb.intercity.bus.telegrambot.BusStationBtnsGeneratorImpl.getKeyboardMarkupForBusStations())")
    public Object getKeyboardMarkupForBusStationsMethodLogger(ProceedingJoinPoint joinPoint){
        Object returnVal = commonLogHandler.handle(joinPoint);
        return returnVal;
    }

//    @Around("execution(* ru.cheb.intercity.bus.telegrambot.BusStationBtnsGeneratorImpl.getBusStationButtons())")
//    public Object  getBusStationButtonsMethodLogger(ProceedingJoinPoint joinPoint){
//        Object returnVal = commonLogHandler.handle(joinPoint);
//        return returnVal;
//    }

}
