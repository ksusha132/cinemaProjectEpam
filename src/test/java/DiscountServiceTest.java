import epam.cinemaProject.configuration.AuditoriumConfig;
import epam.cinemaProject.pojo.cinema.Event;
import epam.cinemaProject.pojo.user.User;
import epam.cinemaProject.services.DiscountService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

class DiscountServiceTest {
    private ApplicationContext ctx = new AnnotationConfigApplicationContext(AuditoriumConfig.class);
    private DiscountService discountService = ctx.getBean("discountService", DiscountService.class);

    @Test
    void getDiscountTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        User user = new User();
        user.setBirthDay("1993-01-04");
        user.setEmail("sese@mail.com");
        user.setId(10L);
        user.setName("Sena");
        user.setLastName("Senov");

        Event event = new Event();
        event.setId(1L);
        event.setBasePrice(200);
        event.setRating("high");
        event.setName("Something");

        assertNotNull(discountService.getDiscount(user, event, LocalDateTime.parse("2018-12-29 10:30", formatter), 10));
        assertEquals(java.util.Optional.of(70).get(), discountService.getDiscount(user, event, LocalDateTime.parse("2018-12-29 10:30", formatter), 10));

        assertNotNull(discountService.getDiscount(user, event, LocalDateTime.parse("2018-11-29 10:30", formatter), 20));
        assertEquals(java.util.Optional.of(5).get(), discountService.getDiscount(user, event, LocalDateTime.parse("2018-11-29 10:30", formatter), 20));

        assertNotNull(discountService.getDiscount(user, event, LocalDateTime.parse("2018-01-06 10:30", formatter), 10));
        assertEquals(java.util.Optional.of(7).get(), discountService.getDiscount(user, event, LocalDateTime.parse("2018-01-05 10:30", formatter), 2));
    }
}
