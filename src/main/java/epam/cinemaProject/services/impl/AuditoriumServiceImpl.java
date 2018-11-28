package epam.cinemaProject.services.impl;

import epam.cinemaProject.pojo.cinema.Auditorium;
import epam.cinemaProject.services.AuditoriumService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class AuditoriumServiceImpl implements AuditoriumService {

    private final String PATH = "/Users/ksusha/Downloads/cinemaProject/src/main/resources/";
    private HashSet<String> set = new HashSet<>();

    private void getListFile() {
        File directory = new File("/Users/ksusha/Downloads/cinemaProject/src/main/resources/");
        FileUtils.listFiles(directory, new WildcardFileFilter("*.properties"), null)
                .forEach(file -> {
                    set.add(file.getName());
                });
    }

    @Override
    public Set<Auditorium> getAllAuditoriums() {
        return getAuditoriumsSet();
    }

    private Set<Auditorium> getAuditoriumsSet() {
        getListFile();
        Properties prop = new Properties();
        Set<Auditorium> auditoriums = new HashSet<>();
        set.forEach(el -> {
            try {
                InputStream inputStream = new FileInputStream(PATH + el);
                prop.load(inputStream);
                Auditorium auditorium = createAuditorium(prop);
                auditoriums.add(auditorium);
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        });
        return auditoriums;
    }

    private Auditorium createAuditorium(Properties prop) {
        Auditorium auditorium = new Auditorium();
        auditorium.setId(ServiceHelper.createID());
        auditorium.setName(prop.getProperty("name"));
        auditorium.setNumberOfSeats(Integer.valueOf(prop.getProperty("numberOfSeats")));
        auditorium.setVipSeats(prop.getProperty("vipSeats"));
        return auditorium;
    }

    @Override
    public Auditorium getByName(String name) {
        Set<Auditorium> set = getAuditoriumsSet();
        return set.stream()
                .filter(auditorium -> auditorium.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("There is no auditorium"));
    }
}
