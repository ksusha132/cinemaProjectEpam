package epam.cinemaProject.pojo.cinema;

import java.util.HashSet;
import java.util.Set;

public class Store {
    static Set<BookedTicket> bookedTickets = new HashSet();

    public static Set<BookedTicket> getBookedTickets() {
        return bookedTickets;
    } // todo create table and put it into db

    public static void setToBoockedTicketsList(BookedTicket bookedTicket) {
        if (bookedTickets.contains(bookedTicket)) {
            throw new RuntimeException("There is an error while book ticket");
        }
        if (bookedTicket == null) {
            throw new RuntimeException("the ticket is empty");
        }
        bookedTickets.add(bookedTicket);
    }
}
