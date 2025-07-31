package service;

import entity.Sale;
import entity.SaleItem;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Date;

public interface SaleService {

    /**
     * 创建销售单
     */
    Sale createSale(int cashierId);

    /**
     * 根据销售单号查找销售单
     */
    Sale findBySaleNo(String saleNo);

    /**
     * 根据ID查找销售单
     */
    Sale findById(int id);

    /**
     * 查找所有销售单
     */
    List<Sale> findAll();

    /**
     * 根据收银员查找销售单
     */
    List<Sale> findByCashier(int cashierId);

    /**
     * 根据会员查找销售单
     */
    List<Sale> findByMember(int memberId);

    /**
     * 根据状态查找销售单
     */
    List<Sale> findByStatus(String status);

    /**
     * 根据日期范围查找销售单
     */
    List<Sale> findByDateRange(Date startDate, Date endDate);

    /**
     * 添加商品到销售单
     */
    boolean addProductToSale(int saleId, String barcode, int quantity);

    /**
     * 从销售单移除商品
     */
    boolean removeProductFromSale(int saleId, int productId);

    /**
     * 更新商品数量
     */
    boolean updateProductQuantity(int saleId, int productId, int quantity);

    /**
     * 设置会员
     */
    boolean setMember(int saleId, String cardNo);

    /**
     * 计算销售单总额
     */
    java.math.BigDecimal calculateTotal(int saleId);

    /**
     * 结账
     */
    boolean checkout(int saleId, String payType);

    /**
     * 挂单
     */
    boolean holdSale(int saleId);

    /**
     * 撤单
     */
    boolean cancelSale(int saleId);

    /**
     * 恢复挂单
     */
    boolean resumeSale(int saleId);

    /**
     * 打印销售小票
     */
    String generateReceipt(int saleId);

    /**
     * 获取销售统计
     */
    java.math.BigDecimal getTotalSales(Date startDate, Date endDate);

    /**
     * 获取销售数量
     */
    int getSaleCount(Date startDate, Date endDate);

    /**
     * 获取挂单列表
     */
    List<Sale> getHoldSales(int cashierId);

    // 基础CRUD操作
    boolean addSale(Sale sale);

    boolean updateSale(Sale sale);

    boolean deleteSale(int id);

    List<Sale> findByDateRange(String startDate, String endDate);

    List<Sale> findByStatus(int status);

    List<SaleItem> getSaleItems(int saleId);

    // 统计操作
    BigDecimal getTodaySales();

    int getTodayOrderCount();

    BigDecimal getMonthSales();

    int getMonthOrderCount();

    int getProductSalesCount(int productId);

    BigDecimal getMemberConsumption(int memberId);

    List<Map<String, Object>> getCashierSalesStats(String startDate, String endDate);

    Map<String, Object> getDailyStats(String date);

    Map<String, Object> getMonthlyStats(String year, String month);
}