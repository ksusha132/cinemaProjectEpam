package epam.cinemaProject.services;

import com.sun.istack.internal.NotNull;
import epam.cinemaProject.pojo.cinema.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    void saveEvent(@NotNull String name, @NotNull String rating, @NotNull double basePrice,
                   @NotNull List<LocalDateTime> dateTime, @NotNull String auditorium);

    void removeEvent(Long id) throws Throwable;

    Event getById(Long id) throws Throwable;

    Event getByName(String name) throws Throwable;

    List<Event> getAll();
}
