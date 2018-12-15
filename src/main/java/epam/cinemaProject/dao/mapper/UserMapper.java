package epam.cinemaProject.dao.mapper;

import epam.cinemaProject.pojo.user.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setBirthDay(String.valueOf(rs.getDate("birthday")));
        user.setRole(rs.getString("role"));
        return user;
    }
}
