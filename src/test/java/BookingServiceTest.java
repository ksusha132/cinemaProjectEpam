import epam.cinemaProject.configuration.AuditoriumConfig;
import epam.cinemaProject.pojo.cinema.BookedTicket;
import epam.cinemaProject.pojo.cinema.Event;
import epam.cinemaProject.pojo.cinema.Rating;
import epam.cinemaProject.pojo.cinema.Store;
import epam.cinemaProject.pojo.user.User;
import epam.cinemaProject.services.BookingService;
import epam.cinemaProject.services.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class BookingServiceTest {

    private ApplicationContext ctx = new AnnotationConfigApplicationContext(AuditoriumConfig.class);
    private BookingService bookingService = ctx.getBean("bookingService", BookingService.class);
    private EventService eventService = ctx.getBean("eventService", EventService.class);

    @Test
    public void getTicketPrice() throws Throwable {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        List<LocalDateTime> dateTimes = new ArrayList<>();
        dateTimes.add(LocalDateTime.parse("2018-12-29 10:00", formatter));
        dateTimes.add(LocalDateTime.parse("2018-12-29 16:00", formatter));
        eventService.saveEvent("dejavue", Rating.HIGH, 100, dateTimes, "redStage");

        User user = new User();
        user.setBirthDay("1993-01-04");
        user.setEmail("sese@mail.com");
        user.setId(10L);
        user.setName("Sena");
        user.setLastName("Senov");
        Event event = eventService.getByName("dejavue");

        assertNotNull(bookingService.getTicketsPrice(event, LocalDateTime.parse("2018-12-29 10:00", formatter),
                user, "34,35"));

        BookedTicket bookedTicket = new BookedTicket();
        bookedTicket.setEvent(event);
        bookedTicket.setTime(LocalDateTime.parse("2018-12-29 10:00", formatter));
        bookedTicket.setSeat(1);

        BookedTicket bookedTicket1 = new BookedTicket();
        bookedTicket1.setEvent(event);
        bookedTicket1.setTime(LocalDateTime.parse("2018-12-29 10:00", formatter));
        bookedTicket1.setSeat(2);

        BookedTicket bookedTicket3 = new BookedTicket();
        bookedTicket3.setEvent(event);
        bookedTicket3.setTime(LocalDateTime.parse("2018-12-29 10:00", formatter));
        bookedTicket3.setSeat(31);

        BookedTicket bookedTicket4 = new BookedTicket();
        bookedTicket4.setEvent(event);
        bookedTicket4.setTime(LocalDateTime.parse("2018-12-29 10:00", formatter));
        bookedTicket4.setSeat(32);
        Store.setToBoockedTicketsList(bookedTicket);
        Store.setToBoockedTicketsList(bookedTicket1);
        Store.setToBoockedTicketsList(bookedTicket3);
        Store.setToBoockedTicketsList(bookedTicket4);

        assertNotNull(bookingService.getNumbersOfBookedTickets(event, LocalDateTime.parse("2018-12-29 10:00", formatter)));
        assertEquals(4, bookingService.getNumbersOfBookedTickets(event, LocalDateTime.parse("2018-12-29 10:00", formatter)).size());

        BookedTicket bookedTicket5 = new BookedTicket();
        bookedTicket5.setEvent(event);
        bookedTicket5.setTime(LocalDateTime.parse("2018-12-29 10:00", formatter));
        bookedTicket5.setSeat(36);

        bookingService.bookTickets(bookedTicket5);
        assertEquals(5, bookingService.getNumbersOfBookedTickets(event, LocalDateTime.parse("2018-12-29 10:00", formatter)).size());

    }
}
