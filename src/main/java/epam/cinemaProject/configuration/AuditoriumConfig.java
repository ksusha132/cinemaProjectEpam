package epam.cinemaProject.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@ComponentScan("epam.cinemaProject")
@PropertySources({
        @PropertySource("classpath:auditorium1SeatsVip.properties"),
        @PropertySource("classpath:auditorium2SeatsVip.properties"),
        @PropertySource("classpath:auditorium3SeatsVip.properties")
})
public class AuditoriumConfig {

}
