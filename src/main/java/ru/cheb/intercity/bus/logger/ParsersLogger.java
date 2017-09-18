package ru.cheb.intercity.bus.logger;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.cheb.intercity.bus.logger.helper.CommonLogHandler;
import ru.cheb.intercity.bus.parsers.BusStationsParser;


/**
 * Function realize logging of methods of parsers in {@link ru.cheb.intercity.bus.parsers} package.
 */
@Aspect
@Component
public class ParsersLogger {

    @Autowired
    CommonLogHandler commonLogHandler;

    /**
     * Function make pointcut in method {@link BusStationsParser#getBusStationsSchedulerUrls()}.
     * @param joinPoint - contain method context;
     * @return - methods return value.
     */
    @Around("execution(* ru.cheb.intercity.bus.parsers.BusStationsParser.getBusStationsSchedulerUrls())")
    public Object getBusStationsSchedulerUrlsMethodLogger(ProceedingJoinPoint joinPoint)
    {
        Object returnVal = commonLogHandler.handle(joinPoint);
        return returnVal;
    }

    /**
     * Function make pointcut in method
     * {@link ru.cheb.intercity.bus.parsers.BusStationSchedulerParser#getScheduler(String)}.
     * @param joinPoint - contain method context;
     * @return - methods return value.
     */
    @Around("execution(* ru.cheb.intercity.bus.parsers.BusStationSchedulerParser.getScheduler())")
    public Object getSchedulerMethodLogger(ProceedingJoinPoint joinPoint){
        Object returnVal = commonLogHandler.handle(joinPoint);
        return returnVal;
    }

    /**
     * Function make pointcut in method
     * {@link ru.cheb.intercity.bus.parsers.BusStationSchedulerDescriptionParser#getSchedulerDescription(String)}.
     * @param joinPoint - contain method context;
     * @return - methods return value.
     */
    @Around("execution(* ru.cheb.intercity.bus.parsers.BusStationSchedulerDescriptionParserImpl.getSchedulerDescription(String))")
    public Object getSchedulerDescriptionMethodLogger(ProceedingJoinPoint joinPoint)
    {
        Object returnVal = commonLogHandler.handle(joinPoint);
        return returnVal;
    }
}
