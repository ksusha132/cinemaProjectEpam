package epam.cinemaProject.services.impl;

import com.sun.istack.internal.NotNull;
import epam.cinemaProject.pojo.user.Role;
import epam.cinemaProject.pojo.user.User;
import epam.cinemaProject.services.UserService;

import java.util.concurrent.ConcurrentHashMap;


public class UserServiceImpl implements UserService {

    private ConcurrentHashMap<Long, User> userList = new ConcurrentHashMap<>();


    @Override
    public User getUserById(@NotNull Long id) {
        return userList.get(id);
    }

    @Override
    public User getByEmail(@NotNull final String email) {
        return userList.values().stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("There is no user with such email"));
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
        userList.put(id, user);
    }

    @Override
    public void deleteUser(@NotNull Long id) {
        // check if user deleted
        userList.remove(id);
    }

    @Override
    public ConcurrentHashMap<Long, User> getAllUsers() {
        return userList;
    }
}
