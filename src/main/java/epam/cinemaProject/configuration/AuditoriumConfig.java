package epam.cinemaProject.configuration;

import org.springframework.context.annotation.*;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("epam.cinemaProject")
@PropertySources({
        @PropertySource("classpath:auditorium1SeatsVip.properties"),
        @PropertySource("classpath:auditorium2SeatsVip.properties"),
        @PropertySource("classpath:auditorium3SeatsVip.properties")
})
public class AuditoriumConfig {

}
