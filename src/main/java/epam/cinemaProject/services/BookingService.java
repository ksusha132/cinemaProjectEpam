package epam.cinemaProject.services;

import epam.cinemaProject.pojo.cinema.BookedTicket;
import epam.cinemaProject.pojo.cinema.Event;
import epam.cinemaProject.pojo.user.User;

import java.time.LocalDateTime;
import java.util.Set;

public interface BookingService {
    Double getTicketsPrice(Event event, LocalDateTime dateTime, User user, String seats) throws Throwable;

    void bookTickets(BookedTicket ticket);

    Set<BookedTicket> getPurchasedTicketsForEvent(Event event, LocalDateTime dateTime);

    Set<Integer> getNumbersOfBookedTickets(Event event, LocalDateTime dateTime);
}
