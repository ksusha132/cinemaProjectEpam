package epam.cinemaProject.pojo.cinema;

import java.time.LocalDateTime;
import java.util.Objects;

public class BookedTicket {
    LocalDateTime time;
    Integer seat;
    private Event event;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookedTicket)) return false;
        BookedTicket that = (BookedTicket) o;
        return Objects.equals(event, that.event) &&
                Objects.equals(time, that.time) &&
                Objects.equals(seat, that.seat);
    }

    @Override
    public int hashCode() {

        return Objects.hash(event, time, seat);
    }
}
