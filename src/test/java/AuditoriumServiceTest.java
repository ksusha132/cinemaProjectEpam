import epam.cinemaProject.configuration.AuditoriumConfig;
import epam.cinemaProject.services.AuditoriumService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


class AuditoriumServiceTest {
    private ApplicationContext ctx = new AnnotationConfigApplicationContext(AuditoriumConfig.class);
    private AuditoriumService auditoriumService = ctx.getBean("auditoriumService", AuditoriumService.class);

    @Test
    void getAllAuditoriumsTest() throws IOException {
        assertEquals(3, auditoriumService.getAllAuditoriums().size());
    }

    @Test
    void getAuditoriumByNameTest() throws IOException {
        assertNotNull(auditoriumService.getByName("redStage"));
        assertEquals(java.util.Optional.of(100).get(), auditoriumService.getByName("redStage").getNumberOfSeats());
        assertNotNull(auditoriumService.getByName("redStage").getVipSeats());
        assertEquals(9, auditoriumService.getByName("redStage").getVipSeats().size());
    }
}
