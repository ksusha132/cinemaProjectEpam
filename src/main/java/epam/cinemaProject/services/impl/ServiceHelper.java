package epam.cinemaProject.services.impl;

import epam.cinemaProject.pojo.cinema.Auditorium;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class ServiceHelper {
    public static AtomicLong idCounter = new AtomicLong(1);

    public static Long createID() {
        return idCounter.incrementAndGet();
    }

    public static Set<Integer> parseSeats(String seats) {

        Set<Integer> integerSet = new HashSet<>();
        String[] setSeat = seats.split(",");

        for (String i : setSeat) {
            integerSet.add(Integer.valueOf(i));
        }

        return integerSet;
    }

    public static Auditorium createAuditorium(Long id, Integer numberOfSeats, String name, String vipSeats) {
        Auditorium auditorium = new Auditorium();
        auditorium.setId(id);
        auditorium.setNumberOfSeats(numberOfSeats);
        auditorium.setName(name);
        auditorium.setVipSeats(ServiceHelper.parseSeats(vipSeats));
        return auditorium;
    }
}
