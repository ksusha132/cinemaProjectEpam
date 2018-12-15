package epam.cinemaProject.services.impl;

import epam.cinemaProject.pojo.cinema.BookedTicket;
import epam.cinemaProject.pojo.cinema.Event;
import epam.cinemaProject.pojo.cinema.Store;
import epam.cinemaProject.pojo.user.User;
import epam.cinemaProject.services.AuditoriumService;
import epam.cinemaProject.services.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service("bookingService")
public class BookingServiceImpl implements epam.cinemaProject.services.BookingService {

    @Autowired
    private DiscountService discountService;
    @Autowired
    private AuditoriumService auditoriumService;

    @Override
    public Double getTicketsPrice(Event event, LocalDateTime dateTime, User user, String seats) throws IOException {
        Set<Integer> vipSeats = auditoriumService.getByName(event.getAuditoriums().firstEntry().getValue().getName()).getVipSeats(); // optional
        Set<Integer> wantedSeats = ServiceHelper.parseSeats(seats);

        Double basePrice = getBasePrice(event);
        Integer discount = discountService.getDiscount(user, event, dateTime, wantedSeats.size()); // 70

        Long countVipSeats = wantedSeats.stream().filter(vipSeats::contains).count(); // count vip ticket
        Long seatsTotal = (long) wantedSeats.size(); // all wanted ticket
        Double priceWithoutDiscount = (basePrice * countVipSeats * 2) + ((seatsTotal - countVipSeats) * basePrice); //100
        Double discountForTickets = (priceWithoutDiscount * discount) / 100;
        return priceWithoutDiscount - discountForTickets;

    }

    private Double getBasePrice(Event event) {
        if (event.getRating().equals("high")) {
            return event.getBasePrice() * 1.2; // the price will be the same for each period of time
        } else {
            return event.getBasePrice();
        }
    }

    @Override
    public void bookTickets(BookedTicket ticket) {
        Store.setToBoockedTicketsList(ticket);
    }

    @Override
    public Set<BookedTicket> getPurchasedTicketsForEvent(Event event, LocalDateTime dateTime) {
        return Store.getBookedTickets();
    }

    @Override
    public Set<Integer> getNumbersOfBookedTickets(Event event, LocalDateTime dateTime) { // at first person see booked seats
        return Store.getBookedTickets().stream()
                .filter(bookedTicket -> bookedTicket.getEvent().equals(event))
                .filter(bookedTicket -> bookedTicket.getTime().equals(dateTime))
                .map(BookedTicket::getSeat)
                .collect(Collectors.toSet());
    }
}
