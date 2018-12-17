package epam.cinemaProject.dao.impl;

import epam.cinemaProject.dao.CounterDao;
import epam.cinemaProject.dao.mapper.CounterMapper;
import epam.cinemaProject.pojo.counter.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("counter")
public class CounterDaoImpl implements CounterDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Counter counter) {
        String qr = "INSERT INTO counter (name, c_type, count) VALUES (?,?,?)";
        jdbcTemplate.update(qr, counter.getName(), counter.getCountType(), counter.getCount());
    }

    @Override
    public void update(Counter counter) {
        String qr = "UPDATE counter SET count = ? WHERE name = ? AND c_type = ?";
        jdbcTemplate.update(qr, counter.getCount(), counter.getName(), counter.getCountType());
    }

    @Override
    public Counter getByNameAndType(String name, String countType) {
        try {
            String SQL = "SELECT * FROM counter WHERE name = ? AND c_type = ?";
            return (Counter) jdbcTemplate.queryForObject(SQL, new Object[]{name, countType}, new CounterMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
