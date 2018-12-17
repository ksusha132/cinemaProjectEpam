package epam.cinemaProject.dao.mapper;

import epam.cinemaProject.pojo.counter.Counter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CounterMapper implements RowMapper {

    @Override
    public Counter mapRow(ResultSet rs, int rowNum) throws SQLException {
        Counter counter = new Counter();
        counter.setName(rs.getString("name"));
        counter.setCountType(rs.getString("c_type"));
        counter.setCount(rs.getInt("count"));
        return counter;
    }
}
