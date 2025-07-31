import dao.impl.CashierDAOImpl;
import entity.Cashier;
import service.UserService;
import service.impl.UserServiceImpl;
import entity.User;
import org.junit.Test;

public class CashierTest {
    private CashierDAOImpl dao = new CashierDAOImpl();
    private UserService userService = new UserServiceImpl();

    @Test
    public void testAddCashier() {
        Cashier cashier = new Cashier();
        cashier.setCashierName("测试收银员");
        cashier.setPhone("13800138000");
        cashier.setPassword("123456");
        cashier.setRoleId(1);

        int result = dao.addCashier(cashier);
        if (result > 0) {
            System.out.println("添加成功");
        } else {
            System.out.println("添加失败");
        }
    }

    @Test
    public void testLogin() {
        // 2. 测试登录
        User user = userService.login("cashier1", "123");
        if (user != null) {
            System.out.println("登录成功: " + user.getUsername());
        } else {
            System.out.println("登录失败");
        }
    }
}