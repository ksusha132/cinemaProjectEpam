package epam.cinemaProject.services.impl;

import com.sun.istack.internal.NotNull;
import epam.cinemaProject.dao.EventDao;
import epam.cinemaProject.pojo.cinema.Event;
import epam.cinemaProject.pojo.cinema.Rating;
import epam.cinemaProject.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private AuditoriumServiceImpl auditoriumService;

    @Autowired
    private EventDao eventDao;

    @Override
    public void saveEvent(@NotNull String name, @NotNull Rating rating, @NotNull double basePrice,
                          @NotNull List<LocalDateTime> dateTimesEvent, @NotNull String auditorium) {
        Event event = createEvent(name, rating, basePrice, dateTimesEvent, auditorium);
        eventDao.save(event);
    }

    private Event createEvent(@NotNull String name, @NotNull Rating rating, @NotNull double basePrice, @NotNull List<LocalDateTime> dateTimesEvent, @NotNull String auditorium) {
        Event event = new Event();
        event.setId(ServiceHelper.createID());
        event.setName(name);
        event.setBasePrice(basePrice);
        event.setRating(rating);
        dateTimesEvent.forEach(dtEvent -> event.addAirDateTime(dtEvent, auditoriumService.getByName(auditorium)));
        return event;
    }

    @Override
    public void removeEvent(Long id) throws Throwable {
        eventDao.remove(id);
    }

    @Override
    public Event getById(Long id) throws Throwable {
        return eventDao.getById(id);
    }

    @Override
    public Event getByName(String name) throws Throwable {
        return eventDao.getByName(name);
    }

    @Override
    public Set<Event> getAll() {
        return eventDao.getAll();
    }
}
