package dao;

import entity.Cashier;
import java.util.List;

public interface CashierDAO {
    int addCashier(Cashier cashier);
    int updateCashier(Cashier cashier);
    int deleteCashier(int cashierId);
    Cashier findCashierById(int cashierId);
    List<Cashier> getAllCashiers();
    
    // 新增：根据手机号查询收银员
    Cashier findCashierByPhone(String phone);
}    