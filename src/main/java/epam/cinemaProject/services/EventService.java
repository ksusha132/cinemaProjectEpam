package epam.cinemaProject.services;

import com.sun.istack.internal.NotNull;
import epam.cinemaProject.pojo.cinema.Event;
import epam.cinemaProject.pojo.cinema.Rating;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface EventService {
    void saveEvent(@NotNull String name, @NotNull Rating rating, @NotNull double basePrice,
                   @NotNull List<LocalDateTime> dateTime, @NotNull String auditorium);

    void removeEvent(Long id) throws Throwable;

    Event getById(Long id) throws Throwable;

    Event getByName(String name) throws Throwable;

    Set<Event> getAll();
}
