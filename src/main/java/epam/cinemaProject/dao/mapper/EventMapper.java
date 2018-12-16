package epam.cinemaProject.dao.mapper;

import epam.cinemaProject.pojo.cinema.Event;
import epam.cinemaProject.services.impl.AuditoriumServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EventMapper implements RowMapper {

    final
    AuditoriumServiceImpl auditoriumService;

    @Autowired
    public EventMapper(AuditoriumServiceImpl auditoriumService) {
        this.auditoriumService = auditoriumService;
    }

    @Override
    public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
        Event event = new Event();
        event.setId(rs.getLong("id"));
        event.setName(rs.getString("name").replaceAll("\\s+", ""));
        event.setRating(rs.getString("rating").replaceAll("\\s+", ""));
        event.setBasePrice(rs.getDouble("base_price"));
        event.setAuditoriumName(rs.getString("auditorium").replaceAll("\\s+", ""));
        try {
            parseStringToLocalDateTime(event, rs.getString("date_time"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return event;
    }

    private void parseStringToLocalDateTime(Event event, String dateAndTimes) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        String[] dateTimes = dateAndTimes.split(";");
        for (String dateTime : dateTimes) {
            if (dateTime != null) {
                event.addAirDateTime(LocalDateTime.parse(dateTime, formatter),
                        auditoriumService.getByName(event.getAuditoriumName()));
            } else {
                throw new RuntimeException("no dates");
            }
        }
    }
}
