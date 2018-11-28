package epam.cinemaProject;

import epam.cinemaProject.pojo.cinema.Auditorium;
import epam.cinemaProject.pojo.user.User;
import epam.cinemaProject.services.UserService;
import epam.cinemaProject.services.impl.UserServiceImpl;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

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
        HashSet<String> set = new HashSet<>();
        File directory = new File("/Users/ksusha/Downloads/cinemaProject/src/main/resources/");
        FileUtils.listFiles(directory, new WildcardFileFilter("*.properties"), null)
                .forEach(file -> set.add(file.getName()));
        Properties prop = new Properties();
        Set<Auditorium> auditoriums = new HashSet<>();
        set.forEach(el -> {
            try {
                InputStream inputStream = new FileInputStream("/Users/ksusha/Downloads/cinemaProject/src/main/resources/" + el);
                prop.load(inputStream);
                Auditorium auditorium = new Auditorium();
                auditorium.setName(prop.getProperty("name"));
                auditorium.setNumberOfSeats(Integer.valueOf(prop.getProperty("numberOfSeats")));
                auditorium.setVipSeats(prop.getProperty("vipSeats"));
                auditoriums.add(auditorium);
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        });

        auditoriums.forEach(auditorium -> System.out.println(auditorium.getName()));
    }
}
