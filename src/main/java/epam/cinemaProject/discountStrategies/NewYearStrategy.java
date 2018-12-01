package epam.cinemaProject.discountStrategies;

import epam.cinemaProject.pojo.cinema.Event;
import epam.cinemaProject.pojo.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class NewYearStrategy implements DiscountCounter {
    //NewYear strategy - give 10%  within 5 days of air date

    @Override
    public Integer countDiscount(User user, Event event, LocalDateTime airDateTime, Integer numberOfTickets) {
        return isDiscountAvailable(airDateTime, user) ? 10 : 0;
    }

    private Boolean isDiscountAvailable(LocalDateTime airDateTime, User user) {
        if (user == null) {
            return false;
        }
        LocalDate newYear = LocalDate.ofYearDay(2018, 365);
        LocalDate airDate = airDateTime.toLocalDate();
        return newYear.isAfter(airDate.minusDays(5)) && newYear.isBefore(airDate.plusDays(5));

    }

}
