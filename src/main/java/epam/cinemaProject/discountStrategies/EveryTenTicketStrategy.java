package epam.cinemaProject.discountStrategies;

import epam.cinemaProject.pojo.cinema.Event;
import epam.cinemaProject.pojo.user.User;

import java.time.LocalDateTime;

public class EveryTenTicketStrategy implements DiscountCounter {
    //Every 10th ticket - give 50% for every 10th ticket purchased by user.
    // This strategy can also be applied for not-registered users if 10 or more tickets are bought

    @Override
    public Integer countDiscount(User user, Event event, LocalDateTime airDateTime, Integer numberOfTickets) {
        if (user != null) {
            Integer tickets = user.getTickets().size() + 1;
            return tickets % 10 == 0 ? 50 : 0; // for logged in user
        }

        if (numberOfTickets >= 10) {
            return 50; // not logged in user
        }

        return 0;
    }
}
