package epam.cinemaProject;

import epam.cinemaProject.services.impl.AuditoriumServiceImpl;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        AuditoriumServiceImpl auditoriumService = new AuditoriumServiceImpl();
        System.out.println(auditoriumService.getAllAuditoriums().size());
        System.out.println(auditoriumService.getByName("redStage").getNumberOfSeats());

    }
}
