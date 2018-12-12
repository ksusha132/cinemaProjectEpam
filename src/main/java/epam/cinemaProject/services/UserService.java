package epam.cinemaProject.services;

import epam.cinemaProject.pojo.user.User;

import java.util.List;

public interface UserService {
    User getUserById(Long id);

    User getByEmail(String email);

    void registerUser(String name, String lastName, String email, String birthDay);

    void deleteUser(Long id);

    List<User> getAllUsers();
}
