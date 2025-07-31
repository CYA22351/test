package dao.impl;

import dao.SaleDAO;
import entity.Sale;
import entity.SaleItem;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaleDAOImpl implements SaleDAO {

    @Override
    public Sale findBySaleNo(String saleNo) {
        String sql = "SELECT s.*, u.username as cashier_name, m.name as member_name, m.card_no as member_card_no " +
                "FROM sale s " +
                "LEFT JOIN user u ON s.cashier_id = u.id " +
                "LEFT JOIN member m ON s.member_id = m.id " +
                "WHERE s.sale_no = ?";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, saleNo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Sale sale = mapResultSetToSale(rs);
                sale.setItems(findItemsBySaleId(sale.getId()));
                return sale;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Sale findById(int id) {
        String sql = "SELECT s.*, u.username as cashier_name, m.name as member_name, m.card_no as member_card_no " +
                "FROM sale s " +
                "LEFT JOIN user u ON s.cashier_id = u.id " +
                "LEFT JOIN member m ON s.member_id = m.id " +
                "WHERE s.id = ?";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Sale sale = mapResultSetToSale(rs);
                sale.setItems(findItemsBySaleId(sale.getId()));
                return sale;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Sale> findAll() {
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT s.*, u.username as cashier_name, m.name as member_name, m.card_no as member_card_no " +
                "FROM sale s " +
                "LEFT JOIN user u ON s.cashier_id = u.id " +
                "LEFT JOIN member m ON s.member_id = m.id " +
                "WHERE s.status = 'COMPLETED' " +
                "ORDER BY s.create_time DESC";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Sale sale = mapResultSetToSale(rs);
                sale.setItems(findItemsBySaleId(sale.getId()));
                sales.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }

    @Override
    public List<Sale> findByCashier(int cashierId) {
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT s.*, u.username as cashier_name, m.name as member_name, m.card_no as member_card_no " +
                "FROM sale s " +
                "LEFT JOIN user u ON s.cashier_id = u.id " +
                "LEFT JOIN member m ON s.member_id = m.id " +
                "WHERE s.cashier_id = ? ORDER BY s.create_time DESC";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cashierId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Sale sale = mapResultSetToSale(rs);
                sale.setItems(findItemsBySaleId(sale.getId()));
                sales.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }

    @Override
    public List<Sale> findByMember(int memberId) {
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT s.*, u.username as cashier_name, m.name as member_name, m.card_no as member_card_no " +
                "FROM sale s " +
                "LEFT JOIN user u ON s.cashier_id = u.id " +
                "LEFT JOIN member m ON s.member_id = m.id " +
                "WHERE s.member_id = ? ORDER BY s.create_time DESC";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, memberId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Sale sale = mapResultSetToSale(rs);
                sale.setItems(findItemsBySaleId(sale.getId()));
                sales.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }

    @Override
    public List<Sale> findByStatus(String status) {
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT s.*, u.username as cashier_name, m.name as member_name, m.card_no as member_card_no " +
                "FROM sale s " +
                "LEFT JOIN user u ON s.cashier_id = u.id " +
                "LEFT JOIN member m ON s.member_id = m.id " +
                "WHERE s.status = ? ORDER BY s.create_time DESC";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Sale sale = mapResultSetToSale(rs);
                sale.setItems(findItemsBySaleId(sale.getId()));
                sales.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }

    @Override
    public List<Sale> findByDateRange(Date startDate, Date endDate) {
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT s.*, u.username as cashier_name, m.name as member_name, m.card_no as member_card_no " +
                "FROM sale s " +
                "LEFT JOIN user u ON s.cashier_id = u.id " +
                "LEFT JOIN member m ON s.member_id = m.id " +
                "WHERE s.create_time BETWEEN ? AND ? ORDER BY s.create_time DESC";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setTimestamp(1, new Timestamp(startDate.getTime()));
            stmt.setTimestamp(2, new Timestamp(endDate.getTime()));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Sale sale = mapResultSetToSale(rs);
                sale.setItems(findItemsBySaleId(sale.getId()));
                sales.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }

    @Override
    public void save(Sale sale) {
        String sql = "INSERT INTO sale (sale_no, cashier_id, member_id, total_amount, discount_amount, " +
                "actual_amount, pay_type, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, sale.getSaleNo());
            stmt.setInt(2, sale.getCashierId());
            stmt.setObject(3, sale.getMemberId());
            stmt.setBigDecimal(4, sale.getTotalAmount());
            stmt.setBigDecimal(5, sale.getDiscountAmount());
            stmt.setBigDecimal(6, sale.getActualAmount());
            stmt.setString(7, sale.getPayType());
            stmt.setString(8, sale.getStatus());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                sale.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Sale sale) {
        String sql = "UPDATE sale SET cashier_id = ?, member_id = ?, total_amount = ?, discount_amount = ?, " +
                "actual_amount = ?, pay_type = ?, status = ?, complete_time = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, sale.getCashierId());
            stmt.setObject(2, sale.getMemberId());
            stmt.setBigDecimal(3, sale.getTotalAmount());
            stmt.setBigDecimal(4, sale.getDiscountAmount());
            stmt.setBigDecimal(5, sale.getActualAmount());
            stmt.setString(6, sale.getPayType());
            stmt.setString(7, sale.getStatus());
            stmt.setTimestamp(8,
                    sale.getCompleteTime() != null ? new java.sql.Timestamp(sale.getCompleteTime().getTime()) : null);
            stmt.setInt(9, sale.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        // 先删除明细，再删除主表
        deleteSaleItems(id);
        String sql = "DELETE FROM sale WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStatus(int id, String status) {
        String sql = "UPDATE sale SET status = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveSaleItem(SaleItem item) {
        // 先查找是否已存在该商品明细
        String selectSql = "SELECT id, quantity FROM sale_item WHERE sale_id = ? AND product_id = ?";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
            selectStmt.setInt(1, item.getSaleId());
            selectStmt.setInt(2, item.getProductId());
            ResultSet rs = selectStmt.executeQuery();
            if (rs.next()) {
                // 已存在，更新数量和金额
                int existId = rs.getInt("id");
                int oldQty = rs.getInt("quantity");
                int newQty = oldQty + item.getQuantity();
                String updateSql = "UPDATE sale_item SET quantity = ?, amount = ? WHERE id = ?";
                try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                    updateStmt.setInt(1, newQty);
                    updateStmt.setBigDecimal(2, item.getPrice().multiply(new java.math.BigDecimal(newQty)));
                    updateStmt.setInt(3, existId);
                    updateStmt.executeUpdate();
                }
            } else {
                // 不存在，插入新明细
                String insertSql = "INSERT INTO sale_item (sale_id, product_id, quantity, price, amount) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
                    insertStmt.setInt(1, item.getSaleId());
                    insertStmt.setInt(2, item.getProductId());
                    insertStmt.setInt(3, item.getQuantity());
                    insertStmt.setBigDecimal(4, item.getPrice());
                    insertStmt.setBigDecimal(5, item.getAmount());
                    insertStmt.executeUpdate();
                    ResultSet genKeys = insertStmt.getGeneratedKeys();
                    if (genKeys.next()) {
                        item.setId(genKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SaleItem> findItemsBySaleId(int saleId) {
        List<SaleItem> items = new ArrayList<>();
        String sql = "SELECT si.*, p.name as product_name, p.barcode as product_barcode " +
                "FROM sale_item si " +
                "LEFT JOIN product p ON si.product_id = p.id " +
                "WHERE si.sale_id = ?";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, saleId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                items.add(mapResultSetToSaleItem(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public void deleteSaleItems(int saleId) {
        String sql = "DELETE FROM sale_item WHERE sale_id = ?";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, saleId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public java.math.BigDecimal getTotalSales(Date startDate, Date endDate) {
        String sql = "SELECT SUM(actual_amount) FROM sale WHERE complete_time BETWEEN ? AND ? AND status = 'COMPLETED'";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setTimestamp(1, new Timestamp(startDate.getTime()));
            stmt.setTimestamp(2, new Timestamp(endDate.getTime()));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                java.math.BigDecimal result = rs.getBigDecimal(1);
                return result != null ? result : java.math.BigDecimal.ZERO;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return java.math.BigDecimal.ZERO;
    }

    @Override
    public int getSaleCount(Date startDate, Date endDate) {
        String sql = "SELECT COUNT(*) FROM sale WHERE complete_time BETWEEN ? AND ? AND status = 'COMPLETED'";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setTimestamp(1, new Timestamp(startDate.getTime()));
            stmt.setTimestamp(2, new Timestamp(endDate.getTime()));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private Sale mapResultSetToSale(ResultSet rs) throws SQLException {
        Sale sale = new Sale();
        sale.setId(rs.getInt("id"));
        sale.setSaleNo(rs.getString("sale_no"));
        sale.setCashierId(rs.getInt("cashier_id"));
        sale.setMemberId(rs.getObject("member_id") != null ? rs.getInt("member_id") : null);
        sale.setTotalAmount(rs.getBigDecimal("total_amount"));
        sale.setDiscountAmount(rs.getBigDecimal("discount_amount"));
        sale.setActualAmount(rs.getBigDecimal("actual_amount"));
        sale.setPayType(rs.getString("pay_type"));
        sale.setStatus(rs.getString("status"));
        sale.setCreateTime(rs.getTimestamp("create_time"));
        sale.setCompleteTime(rs.getTimestamp("complete_time"));
        sale.setCashierName(rs.getString("cashier_name"));
        sale.setMemberName(rs.getString("member_name"));
        sale.setMemberCardNo(rs.getString("member_card_no"));
        return sale;
    }

    private SaleItem mapResultSetToSaleItem(ResultSet rs) throws SQLException {
        SaleItem item = new SaleItem();
        item.setId(rs.getInt("id"));
        item.setSaleId(rs.getInt("sale_id"));
        item.setProductId(rs.getInt("product_id"));
        item.setQuantity(rs.getInt("quantity"));
        item.setPrice(rs.getBigDecimal("price"));
        item.setAmount(rs.getBigDecimal("amount"));
        item.setProductName(rs.getString("product_name"));
        item.setProductBarcode(rs.getString("product_barcode"));
        return item;
    }

    // 删除某销售单下的某个商品明细
    public void deleteSaleItemByProductId(int saleId, int productId) {
        String sql = "DELETE FROM sale_item WHERE sale_id = ? AND product_id = ?";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, saleId);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 更新某销售单下某商品的数量和金额
    public void updateSaleItemQuantity(int saleId, int productId, int quantity, java.math.BigDecimal amount) {
        String sql = "UPDATE sale_item SET quantity = ?, amount = ? WHERE sale_id = ? AND product_id = ?";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantity);
            stmt.setBigDecimal(2, amount);
            stmt.setInt(3, saleId);
            stmt.setInt(4, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}