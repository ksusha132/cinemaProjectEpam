package epam.cinemaProject.services.impl;

import com.sun.istack.internal.NotNull;
import epam.cinemaProject.pojo.cinema.Event;
import epam.cinemaProject.pojo.cinema.Rating;
import epam.cinemaProject.services.EventService;
import org.springframework.beans.factory.annotation.Required;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class EventServiceImpl implements EventService {

    HashSet<Event> events = new HashSet<>();
    private AuditoriumServiceImpl auditoriumService;

    // dejavue - 10:00 - 12:00, 16:00 - 18:00
    // rush - 12:00 - 14:00, --- redStage
    // ghost - 14:00 - 16:00, 18:00 - 20:00

    // fight - 10:00 - 12:00, 16:00 - 18:00
    // Jason - 12:00 - 14:00, --- greenStage
    // Freddy - 14:00 - 16:00, 18:00 - 20:00

    // melancholy - 10:00 - 12:00, 16:00 - 18:00
    // island - 12:00 - 14:00, --- blueStage
    // it - 14:00 - 16:00, 18:00 - 20:00

    private static Predicate<Event> isIdMatch(Long id) {
        return event -> event.getId().equals(id);
    }

    private static Predicate<Event> isNameMatch(String name) {
        return event -> event.getName().equals(name);
    }

    @Required
    public void setStrategies(AuditoriumServiceImpl auditoriumService) {
        this.auditoriumService = auditoriumService;
    }

    @Override
    public void saveEvent(@NotNull String name, @NotNull Rating rating, @NotNull double basePrice,
                          @NotNull List<LocalDateTime> dateTimesEvent, @NotNull String auditorium) {

        // I suppose the auditorium of event is the same for all period!

        Event event = getEvent(name, rating, basePrice, dateTimesEvent, auditorium);
        events.add(event);
    }

    private Event getEvent(@NotNull String name, @NotNull Rating rating, @NotNull double basePrice, @NotNull List<LocalDateTime> dateTimesEvent, @NotNull String auditorium) {
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
        Event event = getEventBy(isIdMatch(id));
        events.remove(event);
    }

    private Event getEventBy(Predicate predicate) throws Throwable {
        return (Event) events.stream()
                .filter(predicate)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("no match"));
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
    public Set<Event> getAll() {
        return events;
    }
}
