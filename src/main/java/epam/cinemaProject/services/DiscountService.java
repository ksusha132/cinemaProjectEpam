package epam.cinemaProject.services;

import epam.cinemaProject.pojo.cinema.Event;
import epam.cinemaProject.pojo.user.User;

import java.time.LocalDateTime;

public interface DiscountService {
    Integer getDiscount(User user, Event event, LocalDateTime dateTime, Integer numberOfTickets);
}
