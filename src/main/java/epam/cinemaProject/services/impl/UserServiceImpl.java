package epam.cinemaProject.services.impl;

import com.sun.istack.internal.NotNull;
import epam.cinemaProject.dao.UserDao;
import epam.cinemaProject.pojo.user.Role;
import epam.cinemaProject.pojo.user.User;
import epam.cinemaProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(@NotNull Long id) {
        return userDao.getById(id);
    }

    @Override
    public User getByEmail(@NotNull final String email) {
        return userDao.getByEmail(email);
    }

    @Override
    public void registerUser(@NotNull String name, @NotNull String lastName, @NotNull String email, String birthDay) {
        User user = new User();
        Long id = ServiceHelper.createID();
        user.setId(id);
        user.setName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setBirthDay(birthDay);
        user.setRole(Role.USER);
        userDao.save(user);
    }

    @Override
    public void deleteUser(@NotNull Long id) {
        // check if user deleted
        userDao.delete(id);
    }

    @Override
    public ConcurrentHashMap<Long, User> getAllUsers() {
        return userDao.getAll();
    }
}
