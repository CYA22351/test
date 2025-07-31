package service;

import entity.Product;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

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
     * 添加商品
     */
    boolean addProduct(Product product);

    /**
     * 更新商品
     */
    boolean updateProduct(Product product);

    /**
     * 删除商品
     */
    boolean deleteProduct(int id);

    /**
     * 更新商品库存
     */
    boolean updateStock(int id, int stock);

    /**
     * 更新商品价格
     */
    boolean updatePrice(int id, BigDecimal price);

    /**
     * 更新商品货架
     */
    boolean updateShelf(int id, String shelfCode);

    /**
     * 商品上架
     */
    boolean putOnShelf(int id, String shelfCode);

    /**
     * 商品下架
     */
    boolean takeOffShelf(int id);

    /**
     * 商品移架
     */
    boolean moveShelf(int id, String newShelfCode);

    /**
     * 检查条码是否存在
     */
    boolean existsByBarcode(String barcode);

    /**
     * 检查库存是否充足
     */
    boolean checkStock(int productId, int quantity);

    /**
     * 减少库存
     */
    boolean reduceStock(int productId, int quantity);

    /**
     * 增加库存
     */
    boolean increaseStock(int productId, int quantity);

    /**
     * 根据名称搜索商品
     */
    List<Product> searchByName(String name);

    /**
     * 获取商品总数
     */
    int getTotalCount();

    /**
     * 获取库存不足的商品数量
     */
    int getLowStockCount();
}