package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Component
@Order(2)
public class MyFirstAspect {
    private Logger logger = Logger.getLogger(MyFirstAspect.class.getName());

   // @Around("execution(* service.*.*(..))")
    @Around("execution(* service.MyService.firstTest(..))")
    public void logMessage(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Before executing from MyFirstAspect");
        joinPoint.proceed();
        logger.info("After executing from MyFirstAspect");
    }

    @Around("execution(* service.MyService.secondTest(..))")
    public void logMessage2(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Before executing " + methodName);
        joinPoint.proceed();
        logger.info("After executing " + methodName);
    }

    @Around("execution(* service.MyService.hello(..))")
    public Object logHour(ProceedingJoinPoint joinPoint) throws Throwable {
       String methodName = joinPoint.getSignature().getName();
       Object [] arguments = joinPoint.getArgs();
       logger.info("Before executing " + methodName + " with params: " + Arrays.asList(arguments));
       Object [] newArguments = {22};
       Object result = joinPoint.proceed(newArguments);
       logger.info("After executing " + methodName + " with params: " + Arrays.asList(newArguments));
       return result;
    //    return "Hello there!";
    }

    @Around("@annotation(MarkedForLogging)")
    public void logMarkedMessage(ProceedingJoinPoint joinPoint) throws Throwable    {
        String methodName = joinPoint.getSignature().getName();
        joinPoint.proceed();
        logger.info("Methos was executed from " + methodName);
    }
}
