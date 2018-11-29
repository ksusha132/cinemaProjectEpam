package epam.cinemaProject.services;

import epam.cinemaProject.pojo.cinema.Event;

import java.util.List;

public interface EventService {
    void saveEvent(); //?

    void removeEvent(Long id);

    Event getById(Long id);

    Event getByName(String name);

    List<Event> getAll();
}
