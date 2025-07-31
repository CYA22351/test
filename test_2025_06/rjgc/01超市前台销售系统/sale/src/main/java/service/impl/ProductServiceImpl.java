package service.impl;

import dao.ProductDAO;
import dao.impl.ProductDAOImpl;
import entity.Product;
import service.ProductService;
import util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO = new ProductDAOImpl();

    @Override
    public Product findByBarcode(String barcode) {
        if (StringUtil.isEmpty(barcode)) {
            return null;
        }
        return productDAO.findByBarcode(barcode);
    }

    @Override
    public Product findById(int id) {
        return productDAO.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public List<Product> findByCategory(int categoryId) {
        return productDAO.findByCategory(categoryId);
    }

    @Override
    public List<Product> findByStatus(int status) {
        return productDAO.findByStatus(status);
    }

    @Override
    public List<Product> findLowStock() {
        return productDAO.findLowStock();
    }

    @Override
    public boolean addProduct(Product product) {
        if (product == null || StringUtil.isEmpty(product.getName()) ||
                StringUtil.isEmpty(product.getBarcode()) || product.getPrice() == null) {
            return false;
        }

        if (productDAO.existsByBarcode(product.getBarcode())) {
            return false;
        }

        try {
            productDAO.save(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateProduct(Product product) {
        if (product == null || product.getId() <= 0) {
            return false;
        }

        try {
            productDAO.update(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteProduct(int id) {
        if (id <= 0) {
            return false;
        }

        try {
            productDAO.delete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateStock(int id, int stock) {
        if (id <= 0 || stock < 0) {
            return false;
        }

        try {
            productDAO.updateStock(id, stock);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updatePrice(int id, java.math.BigDecimal price) {
        if (id <= 0 || price == null || price.compareTo(java.math.BigDecimal.ZERO) < 0) {
            return false;
        }

        try {
            productDAO.updatePrice(id, price);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateShelf(int id, String shelfCode) {
        if (id <= 0) {
            return false;
        }

        try {
            productDAO.updateShelf(id, shelfCode);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean putOnShelf(int id, String shelfCode) {
        if (id <= 0 || StringUtil.isEmpty(shelfCode)) {
            return false;
        }

        Product product = productDAO.findById(id);
        if (product == null) {
            return false;
        }

        try {
            product.setShelfCode(shelfCode);
            product.setStatus(1); // 上架状态
            productDAO.update(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean takeOffShelf(int id) {
        if (id <= 0) {
            return false;
        }

        Product product = productDAO.findById(id);
        if (product == null) {
            return false;
        }

        try {
            product.setStatus(0); // 下架状态
            productDAO.update(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean moveShelf(int id, String newShelfCode) {
        if (id <= 0 || StringUtil.isEmpty(newShelfCode)) {
            return false;
        }

        Product product = productDAO.findById(id);
        if (product == null) {
            return false;
        }

        try {
            product.setShelfCode(newShelfCode);
            productDAO.update(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean existsByBarcode(String barcode) {
        return productDAO.existsByBarcode(barcode);
    }

    @Override
    public boolean checkStock(int productId, int quantity) {
        if (productId <= 0 || quantity <= 0) {
            return false;
        }

        Product product = productDAO.findById(productId);
        if (product == null) {
            return false;
        }

        return product.getStock() >= quantity;
    }

    @Override
    public boolean reduceStock(int productId, int quantity) {
        if (productId <= 0 || quantity <= 0) {
            return false;
        }

        Product product = productDAO.findById(productId);
        if (product == null || product.getStock() < quantity) {
            return false;
        }

        try {
            int newStock = product.getStock() - quantity;
            productDAO.updateStock(productId, newStock);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean increaseStock(int productId, int quantity) {
        if (productId <= 0 || quantity <= 0) {
            return false;
        }

        Product product = productDAO.findById(productId);
        if (product == null) {
            return false;
        }

        try {
            int newStock = product.getStock() + quantity;
            productDAO.updateStock(productId, newStock);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Product> searchByName(String name) {
        if (StringUtil.isEmpty(name)) {
            return new ArrayList<>();
        }
        return productDAO.searchByName(name);
    }


    @Override
    public int getTotalCount() {
        return productDAO.getTotalCount();
    }


    @Override
    public int getLowStockCount() {
        return 0;
    }
}