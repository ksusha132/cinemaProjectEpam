package epam.cinemaProject.pojo.cinema;

import java.util.Objects;
import java.util.Set;

public class Auditorium {
    private String name;
    private Integer numberOfSeats;
    private Set<Integer> vipSeats;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Set<Integer> getVipSeats() {
        return vipSeats;
    }

    public void setVipSeats(Set<Integer> vipSeats) {
        this.vipSeats = vipSeats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Auditorium)) return false;
        Auditorium that = (Auditorium) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(numberOfSeats, that.numberOfSeats) &&
                Objects.equals(vipSeats, that.vipSeats);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, numberOfSeats, vipSeats);
    }
}
