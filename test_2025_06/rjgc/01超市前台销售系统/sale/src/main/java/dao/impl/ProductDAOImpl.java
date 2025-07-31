package dao.impl;

import dao.ProductDAO;
import entity.Product;
import util.DBUtil;
import util.StringUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public Product findByBarcode(String barcode) {
        String sql = "SELECT p.*, c.name as category_name FROM product p " +
                "LEFT JOIN category c ON p.category_id = c.id " +
                "WHERE p.barcode = ?";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, barcode);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToProduct(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product findById(int id) {
        String sql = "SELECT p.*, c.name as category_name FROM product p " +
                "LEFT JOIN category c ON p.category_id = c.id " +
                "WHERE p.id = ?";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToProduct(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.*, c.name as category_name FROM product p " +
                "LEFT JOIN category c ON p.category_id = c.id " +
                "ORDER BY p.id";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                products.add(mapResultSetToProduct(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> findByCategory(int categoryId) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.*, c.name as category_name FROM product p " +
                "LEFT JOIN category c ON p.category_id = c.id " +
                "WHERE p.category_id = ? ORDER BY p.id";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, categoryId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                products.add(mapResultSetToProduct(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> findByStatus(int status) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.*, c.name as category_name FROM product p " +
                "LEFT JOIN category c ON p.category_id = c.id " +
                "WHERE p.status = ? ORDER BY p.id";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, status);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                products.add(mapResultSetToProduct(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> findLowStock() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.*, c.name as category_name FROM product p " +
                "LEFT JOIN category c ON p.category_id = c.id " +
                "WHERE p.stock <= p.min_stock ORDER BY p.stock";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                products.add(mapResultSetToProduct(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void save(Product product) {
        String sql = "INSERT INTO product (name, barcode, category_id, price, cost_price, shelf_code, stock, min_stock, status) "
                +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getBarcode());
            stmt.setObject(3, product.getCategoryId());
            stmt.setBigDecimal(4, product.getPrice());
            stmt.setBigDecimal(5, product.getCostPrice());
            stmt.setString(6, product.getShelfCode());
            stmt.setInt(7, product.getStock());
            stmt.setInt(8, product.getMinStock());
            stmt.setInt(9, product.getStatus());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                product.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product product) {
        String sql = "UPDATE product SET name = ?, barcode = ?, category_id = ?, price = ?, " +
                "cost_price = ?, shelf_code = ?, stock = ?, min_stock = ?, status = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getBarcode());
            stmt.setObject(3, product.getCategoryId());
            stmt.setBigDecimal(4, product.getPrice());
            stmt.setBigDecimal(5, product.getCostPrice());
            stmt.setString(6, product.getShelfCode());
            stmt.setInt(7, product.getStock());
            stmt.setInt(8, product.getMinStock());
            stmt.setInt(9, product.getStatus());
            stmt.setInt(10, product.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM product WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStock(int id, int stock) {
        String sql = "UPDATE product SET stock = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, stock);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePrice(int id, java.math.BigDecimal price) {
        String sql = "UPDATE product SET price = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBigDecimal(1, price);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateShelf(int id, String shelfCode) {
        String sql = "UPDATE product SET shelf_code = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, shelfCode);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean existsByBarcode(String barcode) {
        String sql = "SELECT COUNT(*) FROM product WHERE barcode = ?";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, barcode);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Product mapResultSetToProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setBarcode(rs.getString("barcode"));
        product.setCategoryId(rs.getObject("category_id") != null ? rs.getInt("category_id") : null);
        product.setPrice(rs.getBigDecimal("price"));
        product.setCostPrice(rs.getBigDecimal("cost_price"));
        product.setShelfCode(rs.getString("shelf_code"));
        product.setStock(rs.getInt("stock"));
        product.setMinStock(rs.getInt("min_stock"));
        product.setStatus(rs.getInt("status"));
        product.setCreateTime(rs.getTimestamp("create_time"));
        product.setUpdateTime(rs.getTimestamp("update_time"));
        product.setCategoryName(rs.getString("category_name"));
        return product;
    }

    @Override
    public List<Product> searchByName(String name) {
        List<Product> products = new ArrayList<>();
        if (StringUtil.isEmpty(name)) {
            return products;
        }

        String sql = "SELECT p.*, c.name as category_name FROM product p " +
                "LEFT JOIN category c ON p.category_id = c.id " +
                "WHERE p.name LIKE ?";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                products.add(mapResultSetToProduct(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public int getTotalCount() {
        String sql = "SELECT COUNT(*) FROM product";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int getLowStockCount() {
        String sql = "SELECT COUNT(*) FROM product WHERE stock <= min_stock";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}