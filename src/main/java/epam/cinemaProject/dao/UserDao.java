package epam.cinemaProject.dao;

import epam.cinemaProject.pojo.user.User;

import java.util.concurrent.ConcurrentHashMap;

public interface UserDao {
    User getById(Long id);

    User getByEmail(String email);

    void save(User user);

    void delete(Long id);

    ConcurrentHashMap<Long, User> getAll();
}
