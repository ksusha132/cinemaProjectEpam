package epam.cinemaProject.services.impl;

import epam.cinemaProject.pojo.cinema.Event;
import epam.cinemaProject.pojo.user.User;
import epam.cinemaProject.services.DiscountService;

import java.time.LocalDateTime;

public class DiscountServiceImpl  implements DiscountService {

    @Override
    public Double getDiscount(User user, Event event, LocalDateTime dateTime, Integer numberOfTickets) {
        return null;
    }
}
