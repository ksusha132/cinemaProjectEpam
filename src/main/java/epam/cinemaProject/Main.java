package epam.cinemaProject;

import epam.cinemaProject.pojo.user.User;
import epam.cinemaProject.services.UserService;
import epam.cinemaProject.services.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = context.getBean("userService", UserServiceImpl.class);
        userService.registerUser("Sena", "Senov", "sese@mail.com");
        userService.registerUser("Lena ", "Lenov", "lele@mail.com");
        userService.registerUser("Bena", "Benov", "bebe@mail.com");
        User user = userService.getByEmail("sese@mail.com");
        System.out.println(user.getId());
        User user1 = userService.getUserById(3L);
        System.out.println(user1.getName());
        System.out.println("____________________");
        userService.getAllUsers().values().forEach(user2 -> System.out.println(user2.getName()));
        userService.deleteUser(2L);
        System.out.println("____________________");
        userService.getAllUsers().values().forEach(user3 -> System.out.println(user3.getName()));
    }
}
