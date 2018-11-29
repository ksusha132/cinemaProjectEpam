package epam.cinemaProject.services;

import epam.cinemaProject.pojo.cinema.Event;

import java.time.LocalDateTime;

public interface BookingService {
    Double getTicketPrice();

    void bookTickets();

    void getPurchasedTicketsForEvent(Event event, LocalDateTime dateTime);
}
