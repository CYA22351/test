package dao;

import entity.Product;
import java.util.List;

public interface ProductDAO {
    public List<Product> searchByName(String name);

    public int getTotalCount();

    /**
     * 根据条码查找商品
     */
    Product findByBarcode(String barcode);

    /**
     * 根据ID查找商品
     */
    Product findById(int id);

    /**
     * 查找所有商品
     */
    List<Product> findAll();

    /**
     * 根据分类查找商品
     */
    List<Product> findByCategory(int categoryId);

    /**
     * 根据状态查找商品
     */
    List<Product> findByStatus(int status);

    /**
     * 查找库存不足的商品
     */
    List<Product> findLowStock();

    /**
     * 保存商品
     */
    void save(Product product);

    /**
     * 更新商品
     */
    void update(Product product);

    /**
     * 删除商品
     */
    void delete(int id);

    /**
     * 更新商品库存
     */
    void updateStock(int id, int stock);

    /**
     * 更新商品价格
     */
    void updatePrice(int id, java.math.BigDecimal price);

    /**
     * 更新商品货架
     */
    void updateShelf(int id, String shelfCode);

    /**
     * 检查条码是否存在
     */
    boolean existsByBarcode(String barcode);

    public int getLowStockCount();
}