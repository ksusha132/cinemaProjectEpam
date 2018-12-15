package epam.cinemaProject.dao.impl;

import epam.cinemaProject.dao.EventDao;
import epam.cinemaProject.dao.mapper.EventMapper;
import epam.cinemaProject.pojo.cinema.Event;
import epam.cinemaProject.services.impl.AuditoriumServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository("EventDao")
public class EventDaoImpl implements EventDao {

    @Autowired
    AuditoriumServiceImpl auditoriumService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Event event) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        StringBuilder stringBuilder = new StringBuilder();
        event.getAirDates().forEach(ad -> {
            stringBuilder.append(ad.format(formatter)).append(";");
        });

        String qr = "INSERT INTO event (id, name, rating, base_price, date_time, auditorium) VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(qr, event.getId(), event.getName(), event.getRating(), event.getBasePrice(),
                stringBuilder, event.getAuditoriumName());
    }

    @Override
    public void remove(Long id) {
        String SQL = "DELETE FROM event WHERE id = ?";
        jdbcTemplate.update(SQL, id);
    }

    @Override
    public Event getById(Long id) {
        String SQL = "SELECT * FROM event WHERE id = ?";
        return (Event) jdbcTemplate.queryForObject(SQL, new Object[]{id}, new EventMapper(auditoriumService));
    }

    @Override
    public Event getByName(String name) {
        String SQL = "SELECT * FROM event WHERE name = ?";
        return (Event) jdbcTemplate.queryForObject(SQL, new Object[]{name}, new EventMapper(auditoriumService));
    }

    @Override
    public List<Event> getAll() {
        String SQL = "SELECT * FROM event";
        return jdbcTemplate.query(SQL, new EventMapper(auditoriumService));
    }
}
