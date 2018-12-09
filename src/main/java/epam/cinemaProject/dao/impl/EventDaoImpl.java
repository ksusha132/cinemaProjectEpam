package epam.cinemaProject.dao.impl;

import epam.cinemaProject.dao.EventDao;
import epam.cinemaProject.pojo.cinema.Event;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.function.Predicate;

@Repository("EventDao")
public class EventDaoImpl implements EventDao {

    private HashSet<Event> events = new HashSet<>();

    private static Predicate<Event> isIdMatch(Long id) {
        return event -> event.getId().equals(id);
    }

    private static Predicate<Event> isNameMatch(String name) {
        return event -> event.getName().equals(name);
    }

    @Override
    public void save(Event event) {
        events.add(event);
    }

    @Override
    public void remove(Long id) throws Throwable {
        Event event = getEventBy(isIdMatch(id));
        event.clearAuditoriumAndAirdates();
        events.remove(event);
    }

    @Override
    public Event getById(Long id) throws Throwable {
        return getEventBy(isIdMatch(id));
    }

    @Override
    public Event getByName(String name) throws Throwable {
        return getEventBy(isNameMatch(name));
    }

    @Override
    public HashSet<Event> getAll() {
        return events;
    }

    private Event getEventBy(Predicate predicate) throws Throwable {
        return (Event) events.stream()
                .filter(predicate)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("no match"));
    }
}
