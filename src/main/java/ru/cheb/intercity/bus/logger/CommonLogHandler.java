package ru.cheb.intercity.bus.logger;

import org.aspectj.lang.ProceedingJoinPoint;


public interface CommonLogHandler {
    Object handle(ProceedingJoinPoint joinPoint);
}
