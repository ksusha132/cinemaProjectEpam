package epam.cinemaProject.aspects;

import epam.cinemaProject.dao.DiscountCounterDao;
import epam.cinemaProject.pojo.counter.DiscountCounter;
import epam.cinemaProject.pojo.counter.DiscountType;
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
    public void countBeforeGetByName(ProceedingJoinPoint joinPoint) throws Throwable {
        DiscountType discountType = getDiscount(joinPoint);
        User user = (User) joinPoint.getArgs()[0];
        DiscountCounter dc = discountCounter.getByTypeAndLoggedUser(discountType);
        if (dc != null && discountType != null) {
            DiscountCounter disCount = new DiscountCounter();
            disCount.setCount(disCount.getCount() + 1);
            discountCounter.save(disCount);
        } else if (dc == null && discountType != null && user.getBirthDay() != null) {
            createDiscountCounter(discountType, true);
        } else if (dc == null && discountType != null && user.getBirthDay() == null) {
            createDiscountCounter(discountType, false);
        }
    }

    private void createDiscountCounter(DiscountType discountType, Boolean loggedUser) {
        DiscountCounter discountCounter1 = new DiscountCounter();
        discountCounter1.setCount(1);
        discountCounter1.setLoggedUser(loggedUser);
        discountCounter1.setType(discountType);
        discountCounter.save(discountCounter1);
    }

    private DiscountType getDiscount(ProceedingJoinPoint joinPoint) throws Throwable {
        Integer discount = (Integer) joinPoint.proceed();
        if (discount == 70) {
            return DiscountType.NEYYEAR;
        } else if (discount == 7) {
            return DiscountType.BIRTHDAY;
        } else if (discount == 5) {
            return DiscountType.TENTICKET;
        }
        return null;
    }

    private void createCounter() {
        DiscountCounter dc = new DiscountCounter();
        dc.setLoggedUser(true);
        dc.setType(DiscountType.NEYYEAR);
    }
}
