package epam.cinemaProject.services.impl;

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
}
