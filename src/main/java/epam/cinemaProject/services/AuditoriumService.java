package epam.cinemaProject.services;

import epam.cinemaProject.pojo.cinema.Auditorium;

import java.util.Set;

public interface AuditoriumService {
    Set<Auditorium> getAllAuditoriums();

    Auditorium getByName(String name);
}
