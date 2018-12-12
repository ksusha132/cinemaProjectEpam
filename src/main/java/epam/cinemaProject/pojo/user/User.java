package epam.cinemaProject.pojo.user;

import epam.cinemaProject.pojo.cinema.BookedTicket;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.TreeSet;

public class User {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private String name;

    private String lastName;

    private String email;

    private LocalDate birthDay;

    private String role;

    private NavigableSet<BookedTicket> tickets = new TreeSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public NavigableSet<BookedTicket> getTickets() {
        return tickets;
    }

    public void setTickets(NavigableSet<BookedTicket> tickets) {
        this.tickets = tickets;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private Long id;

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = LocalDate.parse(birthDay, formatter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, lastName, email);
    }
}
