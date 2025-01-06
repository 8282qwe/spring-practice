package aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {

    @Before("execution(public aop.domain.Product aop.service.ProductService.find(String) throws RuntimeException)")
    public void adviceBefore() {
        System.out.println("-- Before Advice --");
    }

    // 접근 지시자 (public), throws (RuntimeException) 생략 가능
    @After("execution(aop.domain.Product aop.service.ProductService.find(String))")
    public void adviceAfter() {
        System.out.println("-- After Advice --");
    }

    // Return 값에 와일드 카드(*) 사용 가능, 매개변수를 .. 으로 생략 가능
    @AfterReturning("execution(* aop.service.ProductService.find(..))")
    public void adviceAfterReturning() {
        System.out.println("-- AfterReturning Advice --");
    }

    // 패키지 이름을 생략할 수 있음 (*..* 을 활용)
    @AfterThrowing(value = "execution(* *..*.ProductService.find(..))",throwing = "throwable")
    public void adviceAfterThrowing(Throwable throwable) {
        System.out.println("-- AfterThrowing["+throwable.getMessage()+"] Advice --");
    }

    // 모든 값에 와일드 카드(*)로 생략 가능
    @Around(value = "execution(* *..*.*(..))")
    public Object adviceAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("-- Around Advice : Before --");

//        Object result = proceedingJoinPoint.proceed();

        Object[] params = {"PC"};
        Object result = proceedingJoinPoint.proceed(params);

        System.out.println("-- Around Advice : After --");

        return result;
    }
}
