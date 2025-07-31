package service.impl;

import dao.CashierDAO;
import dao.impl.CashierDAOImpl;
import entity.Cashier;
import service.CashierService;
import java.util.List;

public class CashierServiceImpl implements CashierService {
    private CashierDAO cashierDAO = new CashierDAOImpl();

    @Override
    public boolean addCashier(String cashierName, String phone, String password, int roleId) {
        return false;
    }

    @Override
    public boolean updateCashier(int cashierId, String cashierName, String phone, String password, int roleId) {
        return false;
    }

    @Override
    public boolean deleteCashier(int cashierId) {
        return false;
    }

    @Override
    public Cashier findCashierById(int cashierId) {
        return null;
    }

    @Override
    public List<Cashier> getAllCashiers() {
        return null;
    }

    @Override
    public Cashier login(String phone, String password) {
        // 1. 根据手机号查询收银员
        Cashier cashier = cashierDAO.findCashierByPhone(phone);
        
        // 2. 验证密码
        if (cashier != null && cashier.getPassword().equals(password)) {
            return cashier;
        }
        return null;
    }

    // 其他方法实现省略...
}    