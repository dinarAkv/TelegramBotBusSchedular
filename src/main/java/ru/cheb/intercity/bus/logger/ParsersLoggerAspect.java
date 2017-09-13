package ru.cheb.intercity.bus.logger;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.cheb.intercity.bus.parsers.BusStationSchedulerParser;

@Aspect
@Component
public class ParsersLoggerAspect {


    final static Logger logger = Logger.getLogger(ParsersLoggerAspect.class);

    @Around("execution(ru.cheb.intercity.bus.parsers.BusStationSchedulerParser get*())")
    public Object getSchedulerLog(ProceedingJoinPoint joinPoint){
        try {
            logger.info("HELLO AOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOPPPPPPPPPPPPPPPPPPPPP");

            Object proceed = joinPoint.proceed();
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        throw new IllegalStateException();
    }


}
