package epam.cinemaProject.aspects;

import epam.cinemaProject.dao.CounterDao;
import epam.cinemaProject.pojo.counter.CountType;
import epam.cinemaProject.pojo.counter.Counter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CounterAspect {
    @Autowired
    private CounterDao counterDao;

    @Pointcut("execution(public * epam.cinemaProject.services.EventService.getByName(String))")
    private void getEventByName() {
    }

    @Before("getEventByName()")
    public void countBefore(JoinPoint joinPoint) {
        String s = String.valueOf(joinPoint.getArgs()[0]);
        Counter counter = new Counter();
        counter.setName(s);
        counter.setCountType(CountType.GET_BY_NAME);
        counterDao.save(counter);
        // simpler?
    }
}
