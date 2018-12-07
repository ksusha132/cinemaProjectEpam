package epam.cinemaProject.services;

import epam.cinemaProject.pojo.cinema.Auditorium;

import java.io.IOException;
import java.util.Set;

public interface AuditoriumService {
    Set<Auditorium> getAllAuditoriums() throws IOException;

    Auditorium getByName(String name);
}
