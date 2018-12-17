package epam.cinemaProject.dao.mapper;

import epam.cinemaProject.pojo.counter.DiscountCounter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscountCounterMapper implements RowMapper {
    @Override
    public DiscountCounter mapRow(ResultSet rs, int rowNum) throws SQLException {
        DiscountCounter counter = new DiscountCounter();
        counter.setLoggedUser(rs.getBoolean("logged_user"));
        counter.setType(rs.getString("type"));
        counter.setCount(rs.getInt("count"));
        return counter;
    }
}
