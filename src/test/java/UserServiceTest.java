import epam.cinemaProject.configuration.AuditoriumConfig;
import epam.cinemaProject.services.UserService;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import static junit.framework.TestCase.assertNotNull;


class UserServiceTest {
    private ApplicationContext ctx = new AnnotationConfigApplicationContext(AuditoriumConfig.class);
    private UserService userService = ctx.getBean("userService", UserService.class);

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    void registerUserTest() {
        userService.registerUser("Ivan", "Ivanov", "iva@mail.ru", "1995-06-14");
        assertNotNull(userService.getByEmail("iva@mail.ru"));
        assertNotNull(userService.getUserById(2L));
    }

    @Test
    void getAllUsersTest() {
        assertNotNull(userService.getAllUsers());
    }

    @Test
    void deleteUserTest() {
        userService.deleteUser(2L);
        try {
            userService.getUserById(2L);
        } catch (EmptyResultDataAccessException e) {

        }
    }
}
