import epam.cinemaProject.services.UserService;
import epam.cinemaProject.services.UserServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


class UserServiceTets {
    @Mock
    private ApplicationContext applicationContext;

    @Mock
    private UserServiceImpl userService;

    @Test
    void registerUserTest(){

    }
}
