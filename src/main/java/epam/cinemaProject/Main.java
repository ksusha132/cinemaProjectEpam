package epam.cinemaProject;

import epam.cinemaProject.pojo.cinema.*;
import epam.cinemaProject.pojo.user.User;
import epam.cinemaProject.services.*;
import epam.cinemaProject.services.impl.UserServiceImpl;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Throwable {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = context.getBean("userService", UserServiceImpl.class);
        userService.registerUser("Sena", "Senov", "sese@mail.com", "1993-02-04");
        userService.registerUser("Lena ", "Lenov", "lele@mail.com", "1993-04-12");
        userService.registerUser("Bena", "Benov", "bebe@mail.com", "1995-06-14");
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
                //auditorium.setVipSeats(prop.getProperty("vipSeats"));
                auditoriums.add(auditorium);
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        });

        auditoriums.forEach(auditorium -> System.out.println(auditorium.getName()
                + auditorium.getNumberOfSeats()
                + auditorium.getVipSeats()));
        String seats = "32,34,45,56,66";


        Set<Integer> setIntegerSeats = new HashSet<>();

        String[] setSeat = seats.split(",");
        for (String i : setSeat) {
            setIntegerSeats.add(Integer.valueOf(i));
        }

        System.out.println("____________________");
        setIntegerSeats.forEach(System.out::println);

        System.out.println("____________________");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        DiscountService discountService = (DiscountService) context.getBean("discountService");
        System.out.println(discountService.getDiscount(user, new Event(), LocalDateTime.parse("2018-12-29 10:30", formatter), 10));


        System.out.println("____________________");


        // dejavue - 10:00 - 12:00, 16:00 - 18:00
        // rush - 12:00 - 14:00, --- redStage
        // ghost - 14:00 - 16:00, 18:00 - 20:00

        // fight - 10:00 - 12:00, 16:00 - 18:00
        // Jason - 12:00 - 14:00, --- greenStage
        // Freddy - 14:00 - 16:00, 18:00 - 20:00

        // melancholy - 10:00 - 12:00, 16:00 - 18:00
        // island - 12:00 - 14:00, --- blueStage
        // it - 14:00 - 16:00, 18:00 - 20:00

        EventService eventService = (EventService) context.getBean("eventService");
        AuditoriumService auditoriumService = (AuditoriumService) context.getBean("auditoriumService");
        auditoriumService.getAllAuditoriums();

        List<LocalDateTime> dateTimes = new ArrayList<>();
        dateTimes.add(LocalDateTime.parse("2018-12-29 10:00", formatter));
        dateTimes.add(LocalDateTime.parse("2018-12-29 16:00", formatter));
        eventService.saveEvent("dejavue", Rating.HIGH, 100, dateTimes, "redStage");
        System.out.println(eventService.getAll().size());
        Event event = eventService.getByName("dejavue");

        BookedTicket bookedTicket = new BookedTicket();
        bookedTicket.setEvent(event);
        bookedTicket.setTime(LocalDateTime.parse("2018-12-29 10:00", formatter));
        bookedTicket.setSeat(1);

        BookedTicket bookedTicket1 = new BookedTicket();
        bookedTicket1.setEvent(event);
        bookedTicket1.setTime(LocalDateTime.parse("2018-12-29 10:00", formatter));
        bookedTicket1.setSeat(2);

        BookedTicket bookedTicket3 = new BookedTicket();
        bookedTicket3.setEvent(event);
        bookedTicket3.setTime(LocalDateTime.parse("2018-12-29 10:00", formatter));
        bookedTicket3.setSeat(31);

        BookedTicket bookedTicket4 = new BookedTicket();
        bookedTicket4.setEvent(event);
        bookedTicket4.setTime(LocalDateTime.parse("2018-12-29 10:00", formatter));
        bookedTicket4.setSeat(32);

        Store.setToBoockedTicketsList(bookedTicket);
        Store.setToBoockedTicketsList(bookedTicket1);
        Store.setToBoockedTicketsList(bookedTicket3);
        Store.setToBoockedTicketsList(bookedTicket4);

        Store.getBookedTickets().forEach(bookedTicketN -> {
            System.out.println(bookedTicketN.getEvent().getName() + bookedTicketN.getSeat() + bookedTicketN.getTime());
        });

        System.out.println("____________________");

        BookingService bookingService = (BookingService) context.getBean("bookingService");
        Set<Integer> integerSet = bookingService.getNumbersOfBookedTickets(event, LocalDateTime.parse("2018-12-29 10:00", formatter));
        integerSet.forEach(System.out::println);
    }
}
