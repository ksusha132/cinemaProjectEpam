package epam.cinemaProject.dao.impl;

import epam.cinemaProject.dao.CounterDao;
import epam.cinemaProject.pojo.counter.Counter;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository("counter")
public class CounterDaoImpl implements CounterDao {

    private HashSet<Counter> set = new HashSet();

    @Override
    public void save(Counter counter) {
        set.add(counter);
    }
}
