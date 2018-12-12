package epam.cinemaProject.dao.impl;

import epam.cinemaProject.dao.UserDao;
import epam.cinemaProject.dao.mapper.UserMapper;
import epam.cinemaProject.pojo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User getById(Long id) {
        String SQL = "SELECT * FROM person WHERE id = ?";
        return (User) jdbcTemplate.queryForObject(SQL, new Object[]{id}, new UserMapper());
    }

    @Override
    public User getByEmail(String email) {
        String SQL = "SELECT * FROM person WHERE email = ?";
        return (User) jdbcTemplate.queryForObject(SQL, new Object[]{email}, new UserMapper());
    }

    @Override
    public void save(User user) {
        String qr = "INSERT INTO person (id, name, last_name, email, birthday, role) VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(qr, user.getId(), user.getName(), user.getLastName(), user.getEmail(),
                java.sql.Date.valueOf(user.getBirthDay()), user.getRole());
    }

    @Override
    public void delete(Long id) {
        String SQL = "DELETE FROM person WHERE id = ?";
        jdbcTemplate.update(SQL, id);
    }

    @Override
    public List<User> getAll() {
        String SQL = "SELECT * FROM person";
        List users = jdbcTemplate.query(SQL, new UserMapper());
        return users;
    }
}
