package epam.cinemaProject.aspects;

import epam.cinemaProject.dao.CounterDao;
import epam.cinemaProject.pojo.cinema.BookedTicket;
import epam.cinemaProject.pojo.cinema.Event;
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

    @Pointcut("execution(public * epam.cinemaProject.services.BookingService.getTicketsPrice(..))")
    private void getTicketsPrice() {
    }

    @Pointcut("execution(public * epam.cinemaProject.services.EventService.getByName(String))")
    private void getEventByName() {
    }

    @Pointcut("execution(public * epam.cinemaProject.services.BookingService.bookTickets(..))")
    private void bookTickets() {
    }

    @Before("getEventByName()")
    public void countBeforeGetByName(JoinPoint joinPoint) {
        String eventName = String.valueOf(joinPoint.getArgs()[0]);
        checkAndSet(eventName, CountType.GET_BY_NAME);
    }

    @Before("getTicketsPrice()")
    public void countBeforeGetTicketPrice(JoinPoint joinPoint) {
        Event event = (Event) joinPoint.getArgs()[0];
        String eventName = event.getName();
        checkAndSet(eventName, CountType.GET_EVENT_PRICE);
    }

    @Before("bookTickets()")
    public void countBeforeBookTickets(JoinPoint joinPoint) {
        BookedTicket bookedTicket = (BookedTicket) joinPoint.getArgs()[0];
        String eventName = bookedTicket.getEvent().getName();
        checkAndSet(eventName, CountType.BOOK_TICKETS);
    }

    private void checkAndSet(String eventName, CountType type) {
        Counter counter = counterDao.getByName(eventName, type);
        if (counter != null) {
            incrementAndSetCounter(counter);
        } else {
            createCounter(eventName, type);
        }
    }

    private void incrementAndSetCounter(Counter counter) {
        counter.setCount(counter.getCount() + 1);
        counterDao.save(counter);
    }

    private void createCounter(String s, CountType countType) {
        Counter counter1 = new Counter();
        counter1.setName(s);
        counter1.setCount(1);
        counter1.setCountType(countType);
        counterDao.save(counter1);
    }
}
