package dao;

import entity.Sale;
import entity.SaleItem;
import java.util.List;
import java.util.Date;

public interface SaleDAO {

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
     * 保存销售单
     */
    void save(Sale sale);

    /**
     * 更新销售单
     */
    void update(Sale sale);

    /**
     * 删除销售单
     */
    void delete(int id);

    /**
     * 更新销售单状态
     */
    void updateStatus(int id, String status);

    /**
     * 保存销售明细
     */
    void saveSaleItem(SaleItem item);

    /**
     * 根据销售单ID查找明细
     */
    List<SaleItem> findItemsBySaleId(int saleId);

    /**
     * 删除销售单的所有明细
     */
    void deleteSaleItems(int saleId);

    /**
     * 获取销售统计
     */
    java.math.BigDecimal getTotalSales(Date startDate, Date endDate);

    /**
     * 获取销售数量
     */
    int getSaleCount(Date startDate, Date endDate);
}