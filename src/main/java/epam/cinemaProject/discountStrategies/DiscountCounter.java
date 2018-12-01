package epam.cinemaProject.discountStrategies;

import epam.cinemaProject.pojo.cinema.Event;
import epam.cinemaProject.pojo.user.User;

import java.time.LocalDateTime;

public interface DiscountCounter {
    Integer countDiscount(User user, Event event, LocalDateTime airDateTime, Integer numberOfTickets);
}
