package epam.cinemaProject.dao.impl;

import epam.cinemaProject.dao.DiscountCounterDao;
import epam.cinemaProject.pojo.counter.DiscountCounter;
import epam.cinemaProject.pojo.counter.DiscountType;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository("discountCounterDao")
public class DiscountCounterDaoImpl implements DiscountCounterDao {

    HashSet<DiscountCounter> set = new HashSet<>();

    @Override
    public void save(DiscountCounter counter) {
        set.add(counter);
    }

    @Override
    public DiscountCounter getByTypeAndLoggedUser(DiscountType type) {
        return set.stream()
                .filter(elem -> elem.getType() == type && elem.getLoggedUser())
                .findFirst()
                .orElse(null);
    }
}
