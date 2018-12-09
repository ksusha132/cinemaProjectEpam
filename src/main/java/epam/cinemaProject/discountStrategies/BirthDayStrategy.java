package epam.cinemaProject.discountStrategies;

import epam.cinemaProject.pojo.cinema.Event;
import epam.cinemaProject.pojo.user.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component("birthDayStrategy")
public class BirthDayStrategy implements DiscountCounter {
    //Birthday strategy - give 7% if user has birthday within 5 days of air date

    @Override
    public Integer countDiscount(User user, Event event, LocalDateTime airDateTime, Integer numberOfTickets) {
        return isDiscountAvailable(airDateTime, user) ? 7 : 0;
    }

    private Boolean isDiscountAvailable(LocalDateTime airDateTime, User user) {
        if (user == null) {
            return false;
        }
        int birthDay = user.getBirthDay().getDayOfYear();
        int airDate = airDateTime.toLocalDate().getDayOfYear();
        return (birthDay > airDate - 5) && (birthDay < birthDay + 5);

    }
}
