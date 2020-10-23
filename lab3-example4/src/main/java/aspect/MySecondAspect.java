package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
@Order(1)
public class MySecondAspect {

    private Logger logger = Logger.getLogger(MySecondAspect.class.getName());

    @Around("execution(* service.MyService.firstTest(..))")
    public void logAnotherMessage(ProceedingJoinPoint joinPoint) throws Throwable   {
        logger.info("Before executing from MySecondAspect");
        joinPoint.proceed();
        logger.info("After executing from MySecondAspect");
    }
}
