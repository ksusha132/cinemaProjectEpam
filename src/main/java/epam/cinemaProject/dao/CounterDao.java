package epam.cinemaProject.dao;

import epam.cinemaProject.pojo.counter.CountType;
import epam.cinemaProject.pojo.counter.Counter;

public interface CounterDao {
    void save(Counter counter);

    Counter getByName(String name, CountType type);
}
