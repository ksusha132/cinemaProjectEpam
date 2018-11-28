package epam.cinemaProject.services;

import com.sun.istack.internal.NotNull;
import epam.cinemaProject.pojo.user.Role;
import epam.cinemaProject.pojo.user.User;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class UserServiceImpl implements UserService {

    private ConcurrentHashMap<Long, User> userList = new ConcurrentHashMap<>();

    private static AtomicLong idCounter = new AtomicLong(1);

    private static Long createID() {
        return idCounter.incrementAndGet();
    }

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
    public void registerUser(@NotNull String name, @NotNull String lastName, @NotNull String email) {
        User user = new User();
        Long id = createID();
        user.setId(id);
        user.setName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setRole(Role.USER); // default value
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
