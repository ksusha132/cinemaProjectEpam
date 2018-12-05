package epam.cinemaProject.dao;

import epam.cinemaProject.pojo.cinema.Event;

import java.util.HashSet;

public interface EventDao {
    void save(Event event);

    void remove(Long id) throws Throwable;

    Event getById(Long id) throws Throwable;

    Event getByName(String name) throws Throwable;

    HashSet<Event> getAll();
}
