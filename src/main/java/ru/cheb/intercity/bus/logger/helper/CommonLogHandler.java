package ru.cheb.intercity.bus.logger.helper;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Interface contain common method for logging most of methods.
 */
public interface CommonLogHandler {
    Object handle(ProceedingJoinPoint joinPoint);
}
