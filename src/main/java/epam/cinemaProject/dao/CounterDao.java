package epam.cinemaProject.dao;

import epam.cinemaProject.pojo.counter.Counter;

public interface CounterDao {
    void save(Counter counter);

    void update(Counter counter);

    Counter getByNameAndType(String name, String type);
}
