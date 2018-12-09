import epam.cinemaProject.configuration.AuditoriumConfig;
import epam.cinemaProject.pojo.cinema.Rating;
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


public class EventServiceTest {
    private ApplicationContext ctx = new AnnotationConfigApplicationContext(AuditoriumConfig.class);
    private EventService eventService = ctx.getBean("eventService", EventService.class);

    @Test
    public void createEventTest() throws Throwable {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        List<LocalDateTime> dateTimes = new ArrayList<>();
        dateTimes.add(LocalDateTime.parse("2018-12-29 10:00", formatter));
        dateTimes.add(LocalDateTime.parse("2018-12-29 16:00", formatter));
        eventService.saveEvent("dejavue", Rating.HIGH, 100, dateTimes, "redStage");
        assertNotNull(eventService.getByName("dejavue"));
        assertEquals(1, eventService.getAll().size());
        eventService.removeEvent(2L);
    }
}
