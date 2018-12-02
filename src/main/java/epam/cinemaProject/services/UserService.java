package epam.cinemaProject.services;

import epam.cinemaProject.pojo.user.User;

import java.util.concurrent.ConcurrentHashMap;

public interface UserService {
    User getUserById(Long id);

    User getByEmail(String email);

    void registerUser(String name, String lastName, String email, String birthDay);

    void deleteUser(Long id);

    ConcurrentHashMap<Long, User> getAllUsers();
}
