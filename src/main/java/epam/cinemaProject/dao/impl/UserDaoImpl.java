package epam.cinemaProject.dao.impl;

import epam.cinemaProject.dao.UserDao;
import epam.cinemaProject.pojo.user.User;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository("UserDao")
public class UserDaoImpl implements UserDao {

    private ConcurrentHashMap<Long, User> userList = new ConcurrentHashMap<>();

    @Override
    public User getById(Long id) {
        return userList.get(id);
    }

    @Override
    public User getByEmail(String email) {
        return userList.values().stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("There is no user with such email"));
    }

    @Override
    public void save(User user) {
        userList.put(user.getId(), user);
    }

    @Override
    public void delete(Long id) {
        userList.remove(id);
    }

    @Override
    public ConcurrentHashMap<Long, User> getAll() {
        return userList;
    }
}
