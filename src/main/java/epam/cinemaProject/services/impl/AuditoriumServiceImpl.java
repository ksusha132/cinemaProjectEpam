package epam.cinemaProject.services.impl;

import epam.cinemaProject.pojo.cinema.Auditorium;
import epam.cinemaProject.services.AuditoriumService;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

@Service("AuditoriumService")
public class AuditoriumServiceImpl implements AuditoriumService {

    private Set<Auditorium> auditoriumSet = new HashSet<>();

    private void fillInAuditoriumSet() throws IOException {
        auditoriumSet.add(parseAuditorium("auditorium1SeatsVip.properties"));
        auditoriumSet.add(parseAuditorium("auditorium2SeatsVip.properties"));
        auditoriumSet.add(parseAuditorium("auditorium3SeatsVip.properties"));
    }

    @Override
    public Set<Auditorium> getAllAuditoriums() throws IOException {
        fillInAuditoriumSet();
        return auditoriumSet;
    }

    @Override
    public Auditorium getByName(String name) {
        return auditoriumSet.stream()
                .filter(auditorium -> auditorium.getName().equalsIgnoreCase(name))
                .findFirst().orElseThrow(() -> new RuntimeException("No auditorium"));
    }

    private Auditorium parseAuditorium(String props) throws IOException {
        Properties properties = PropertiesLoaderUtils.loadAllProperties(props);
        Auditorium auditorium = new Auditorium();
        auditorium.setId(ServiceHelper.createID());
        auditorium.setName(properties.getProperty("name"));
        auditorium.setNumberOfSeats(Integer.parseInt(properties.getProperty("numberOfSeats")));
        auditorium.setVipSeats(ServiceHelper.parseSeats(properties.getProperty("vipSeats")));
        return auditorium;
    }
}
