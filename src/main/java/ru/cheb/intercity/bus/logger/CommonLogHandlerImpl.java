package ru.cheb.intercity.bus.logger;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.cheb.intercity.bus.constants.EnvironmentVarConstants;
import ru.cheb.intercity.bus.helper.EnvVarHelper;
import ru.cheb.intercity.bus.logger.helper.PrintSelector;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Class contain common method for logging most of methods.
 */
@Aspect
@Component
public class CommonLogHandlerImpl implements CommonLogHandler {

    final static Logger logger = Logger.getLogger(CommonLogHandlerImpl.class);

    private static final String CALL_METHOD = "\n\n================ Call method ";

    private static final String ARGUMENT_VALUES = "\n\nArgument values: ";
    private static final String ARGUMENT_VALUE = "\n\nArgument value: ";

    private static final String RETURN_VALUE = "\n\nReturn value: ";
    private static final String RETURN_VALUES = "\n\nReturn values: ";

    private static final String NO_ARGUMENTS = "\n\n No arguments";
    private static final String NO_RETURN_VALUES = "\n\n No return value.";

    private static final String END_METHOD = "\n\n==================== End method ";

    @Autowired
    PrintSelector printSelector;

    @Autowired
    EnvVarHelper envVarHelper;



    /**
     * Function realize common logging of methods.
     * @param joinPoint - contain method context.
     * @return - return method value.
     */
    @Around("@annotation(ru.cheb.intercity.bus.logger.MethodLogger) && execution(public * *(..))")
    @Override
    public Object handle(ProceedingJoinPoint joinPoint)
    {

        if (getEnvLogSwitcherVar()){
            System.out.println(CALL_METHOD + joinPoint.getSignature().toShortString());

            printArgumentValues(joinPoint);

            Object returnObj = printReturnValue(joinPoint);

            return returnObj;
        }
        else {
            try {
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                logger.error(throwable);
            }
        }


        throw new IllegalStateException();
    }


    /**
     * Function return enviroment variable for switch on (off) logger.
     * @return true - logger switch on, false - logger switch off.
     */
    private boolean getEnvLogSwitcherVar(){

        String envValue = envVarHelper.getEnvVar(EnvironmentVarConstants.logSwitcher);

        boolean switcher = Boolean.parseBoolean(envValue);

        return switcher;
    }



    /**
     * Function print input arguments of method uses different strategy
     * depends on type of input argument.
     * @param joinPoint - contain method context.
     */
    private void printArgumentValues(ProceedingJoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        if (args.length != 0){

            Arrays.stream(args).forEach(argument->{

                if (argument != null){

                    if (argument.getClass().isArray()){
                        System.out.println("");
                        printSelector.printArray(argument);
                    }
                    else if (argument instanceof List){
                        System.out.println(ARGUMENT_VALUES);
                        printSelector.printList(argument);
                    }
                    else if (argument instanceof Map){
                        System.out.println(ARGUMENT_VALUES);
                        printSelector.printMap(argument);
                    }
                    else {
                        System.out.println(ARGUMENT_VALUE);
                        System.out.println(argument);
                    }
                }

            });

        }
        else {
            System.out.println(NO_ARGUMENTS);
        }
    }


    /**
     * Function print return value depending from its type.
     * @param joinPoint - contain method context.
     * @return - returned value by method.
     */
    private Object printReturnValue(ProceedingJoinPoint joinPoint){
        try {
            Object returnValue = joinPoint.proceed();
            if (returnValue != null){


                if (returnValue.getClass().isArray()){
                    System.out.println(RETURN_VALUES);
                    printSelector.printArray(returnValue);
                }
                else if (returnValue instanceof List){
                    System.out.println(RETURN_VALUES);
                    printSelector.printList(returnValue);
                }
                else if (returnValue instanceof Map){
                    System.out.println(RETURN_VALUES);
                    printSelector.printMap(returnValue);
                }
                else {
                    System.out.println(RETURN_VALUE);
                    System.out.print(returnValue);
                }
            }
            else {
                System.out.println(NO_RETURN_VALUES);
            }

            System.out.println(END_METHOD + " " + joinPoint.getSignature().toShortString());

            return returnValue;
        } catch (Throwable throwable) {
            logger.error(throwable);
        }

        throw new IllegalStateException();
    }

}
