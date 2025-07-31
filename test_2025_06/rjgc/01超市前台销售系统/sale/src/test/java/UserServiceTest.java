import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.UserService;
import service.impl.UserServiceImpl;
import entity.User;
import java.util.List;

public class UserServiceTest {
    private UserService userService;

    @Before
    public void setUp() {
        userService = new UserServiceImpl();
    }

    @Test
    public void testLogin() {
        User user = userService.login("admin", "123");
        Assert.assertNotNull(user);
        Assert.assertEquals("admin", user.getUsername());
    }

    @Test
    public void testRegisterAndFind() {
        User newUser = new User("testuser", "testpass", "member");
        userService.register(newUser);
        User found = userService.findByUsername("testuser");
        Assert.assertNotNull(found);
        Assert.assertEquals("testuser", found.getUsername());
    }

    @Test
    public void testListAll() {
        List<User> users = userService.listAll();
        Assert.assertTrue(users.size() > 0);
    }
}