package epam.cinemaProject.services.impl;

import epam.cinemaProject.pojo.cinema.Event;
import epam.cinemaProject.services.DiscountService;
import org.springframework.beans.factory.annotation.Required;

import java.time.LocalDateTime;

public class BookingServiceImpl implements epam.cinemaProject.services.BookingService{

    private DiscountService discountService;

    @Required
    public void setDiscountService(DiscountService discountService) {
        this.discountService = discountService;
    }

    @Override
    public Double getTicketPrice() {
        return null;
    }

    @Override
    public void bookTickets() {

    }

    @Override
    public void getPurchasedTicketsForEvent(Event event, LocalDateTime dateTime) {

    }

    private boolean isAvailable() {
        return false;
    }
}
