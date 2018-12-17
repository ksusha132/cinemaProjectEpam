package epam.cinemaProject.dao;

import epam.cinemaProject.pojo.counter.DiscountCounter;

public interface DiscountCounterDao {
    void save(DiscountCounter counter);

    void update(DiscountCounter counter);

    DiscountCounter getByTypeAndLoggedUser(String type, Boolean loggedUser);
}
