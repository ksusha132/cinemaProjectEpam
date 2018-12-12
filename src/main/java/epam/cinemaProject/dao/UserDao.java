package epam.cinemaProject.dao;

import epam.cinemaProject.pojo.user.User;

import java.util.List;

public interface UserDao {
    User getById(Long id);

    User getByEmail(String email);

    void save(User user);

    void delete(Long id);

    List<User> getAll();
}
