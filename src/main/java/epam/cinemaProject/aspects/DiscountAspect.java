package epam.cinemaProject.aspects;

import epam.cinemaProject.dao.DiscountCounterDao;
import epam.cinemaProject.pojo.counter.DiscountCounter;
import epam.cinemaProject.pojo.user.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DiscountAspect {
    // count how many times each discount was given total and for specific user - total - for all and specific user - logged in user

    @Autowired
    DiscountCounterDao discountCounter;

    @Pointcut("execution(public * epam.cinemaProject.services.DiscountService.getDiscount(..))")
    private void getDiscount() {
    }

    @Around("getDiscount()")
    public Integer countBeforeGetByName(ProceedingJoinPoint joinPoint) throws Throwable {
        String discountType = getDiscount(joinPoint);
        User user = (User) joinPoint.getArgs()[0];
        Boolean loggedUser = user.getBirthDay() != null;
        DiscountCounter dc = discountCounter.getByTypeAndLoggedUser(discountType, loggedUser);
        if (dc != null && discountType != null) {
            dc.setCount(dc.getCount() + 1);
            discountCounter.update(dc);
        } else if (dc == null && discountType != null && user.getBirthDay() != null) {
            createDiscountCounter(discountType, true);

        } else if (dc == null && discountType != null && user.getBirthDay() == null) {
            createDiscountCounter(discountType, false);
        }
        return (Integer) joinPoint.proceed();
    }

    private void createDiscountCounter(String discountType, Boolean loggedUser) {
        DiscountCounter dc = new DiscountCounter();
        dc.setCount(1);
        dc.setLoggedUser(loggedUser);
        dc.setType(discountType);
        discountCounter.save(dc);
    }

    private String getDiscount(ProceedingJoinPoint joinPoint) throws Throwable {
        Integer discount = (Integer) joinPoint.proceed();
        if (discount == 70) {
            return "NEYYEAR";
        } else if (discount == 7) {
            return "BIRTHDAY";
        } else if (discount == 5) {
            return "TENTICKET";
        }
        return null;
    }
}
