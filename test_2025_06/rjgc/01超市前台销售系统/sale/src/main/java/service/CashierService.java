package service;

import entity.Cashier;
import java.util.List;

public interface CashierService {
    boolean addCashier(String cashierName, String phone, String password, int roleId);
    boolean updateCashier(int cashierId, String cashierName, String phone, String password, int roleId);
    boolean deleteCashier(int cashierId);
    Cashier findCashierById(int cashierId);
    List<Cashier> getAllCashiers();
    
    // 新增：收银员登录方法
    Cashier login(String phone, String password);
}    