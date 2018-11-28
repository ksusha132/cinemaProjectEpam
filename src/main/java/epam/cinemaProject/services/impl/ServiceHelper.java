package epam.cinemaProject.services.impl;

import java.util.concurrent.atomic.AtomicLong;

public class ServiceHelper {
    public static AtomicLong idCounter = new AtomicLong(1);

    public static Long createID() {
        return idCounter.incrementAndGet();
    }
}
