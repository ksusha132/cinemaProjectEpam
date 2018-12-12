package epam.cinemaProject.services.impl;

import com.sun.istack.internal.NotNull;
import epam.cinemaProject.dao.UserDao;
import epam.cinemaProject.pojo.user.User;
import epam.cinemaProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    public UserDao userDao;

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
        user.setId(ServiceHelper.createID());
        user.setName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setBirthDay(birthDay);
        user.setRole("USER");
        userDao.save(user);
    }

    @Override
    public void deleteUser(@NotNull Long id) {
        userDao.delete(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAll();
    }
}
