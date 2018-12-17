package epam.cinemaProject.dao.impl;

import epam.cinemaProject.dao.DiscountCounterDao;
import epam.cinemaProject.dao.mapper.DiscountCounterMapper;
import epam.cinemaProject.pojo.counter.DiscountCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("discountCounterDao")
public class DiscountCounterDaoImpl implements DiscountCounterDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(DiscountCounter counter) {
        String qr = "INSERT INTO d_counter (logged_user, type, count) VALUES (?,?,?)";
        jdbcTemplate.update(qr, counter.getLoggedUser(), counter.getType(), counter.getCount());
    }

    @Override
    public void update(DiscountCounter counter) {
        String qr = "UPDATE d_counter SET count = ? WHERE type = ? AND logged_user = ?";
        jdbcTemplate.update(qr, counter.getCount(), counter.getType(), counter.getLoggedUser());
    }

    @Override
    public DiscountCounter getByTypeAndLoggedUser(String type, Boolean loggedUser) {
        try {
            String SQL = "SELECT * FROM d_counter WHERE type = ? AND logged_user = ?";
            return (DiscountCounter) jdbcTemplate.queryForObject(SQL, new Object[]{type, loggedUser}, new DiscountCounterMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
