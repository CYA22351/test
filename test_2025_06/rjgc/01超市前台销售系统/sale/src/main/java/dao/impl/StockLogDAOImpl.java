package dao.impl;

import dao.StockLogDAO;
import entity.StockLog;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class StockLogDAOImpl implements StockLogDAO {
    @Override
    public void addLog(StockLog log) {
        String sql = "INSERT INTO stock_log (product_id, change_type, before_stock, after_stock, change_quantity, operator_id, change_time, remark) "
                +
                "VALUES (?, ?, ?, ?, ?, ?, NOW(), ?)";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, log.getProductId());
            stmt.setString(2, log.getChangeType());
            stmt.setInt(3, log.getBeforeStock());
            stmt.setInt(4, log.getAfterStock());
            stmt.setInt(5, log.getChangeQuantity());
            stmt.setInt(6, log.getOperatorId());
            stmt.setString(7, log.getRemark());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}