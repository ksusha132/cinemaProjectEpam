package epam.cinemaProject.configuration;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("epam.cinemaProject")
@PropertySources({
        @PropertySource("classpath:auditorium1SeatsVip.properties"),
        @PropertySource("classpath:auditorium2SeatsVip.properties"),
        @PropertySource("classpath:auditorium3SeatsVip.properties")
})
public class AuditoriumConfig {

    @Bean
    public DataSource myPostgresSqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5469/cinema");
        dataSource.setUsername("postgres");
        dataSource.setPassword("root");

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(myPostgresSqlDataSource());
        return jdbcTemplate;
    }
}
