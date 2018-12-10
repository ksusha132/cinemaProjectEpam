package epam.cinemaProject.dao;

import epam.cinemaProject.pojo.counter.DiscountCounter;
import epam.cinemaProject.pojo.counter.DiscountType;

public interface DiscountCounterDao {
    void save(DiscountCounter counter);

    DiscountCounter getByTypeAndLoggedUser(DiscountType type);
}
