package epam.cinemaProject.services.impl;

import epam.cinemaProject.discountStrategies.DiscountCounter;
import epam.cinemaProject.pojo.cinema.Event;
import epam.cinemaProject.pojo.user.User;
import epam.cinemaProject.services.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service("discountService")
public class DiscountServiceImpl implements DiscountService {
    private List<DiscountCounter> strategies;

    @Autowired
    public void setStrategies(List<DiscountCounter> strategies) {
        this.strategies = strategies;
    }

    @Override
    public Integer getDiscount(User user, Event event, LocalDateTime airDateTime, Integer numberOfTickets) {
        // check if  every discount available if 3 of them - get the higher one
        return Optional.of(strategies.stream()
                .mapToInt(strategy -> strategy.countDiscount(user, event, airDateTime, numberOfTickets))
                .max()
                .getAsInt())
                .orElse(0);
    }
}
