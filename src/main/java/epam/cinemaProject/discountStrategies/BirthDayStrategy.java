package epam.cinemaProject.discountStrategies;

import epam.cinemaProject.pojo.cinema.Event;
import epam.cinemaProject.pojo.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BirthDayStrategy implements DiscountCounter {
    //Birthday strategy - give 5% if user has birthday within 5 days of air date

    @Override
    public Integer countDiscount(User user, Event event, LocalDateTime airDateTime, Integer numberOfTickets) {
        return isDiscountAvailable(airDateTime, user) ? 5 : 0;
    }

    private Boolean isDiscountAvailable(LocalDateTime airDateTime, User user) {
        if (user == null) {
            return false;
        }
        LocalDate birthDay = user.getBirthDay(); // day and month
        LocalDate airDate = airDateTime.toLocalDate(); // day and month need
        return birthDay.isAfter(airDate.minusDays(5)) && birthDay.isBefore(airDate.plusDays(5));

    }
}
