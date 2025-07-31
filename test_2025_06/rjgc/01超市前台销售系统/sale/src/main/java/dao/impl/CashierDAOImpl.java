package dao.impl;

import dao.CashierDAO;
import entity.Cashier;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CashierDAOImpl implements CashierDAO {

    @Override
    public int addCashier(Cashier cashier) {
        String sql = "INSERT INTO cashier(cashier_Name, phone, password, roleid) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cashier.getCashierName());
            pstmt.setString(2, cashier.getPhone());
            pstmt.setString(3, cashier.getPassword());
            pstmt.setInt(4, cashier.getRoleId());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateCashier(Cashier cashier) {
        String sql = "UPDATE cashier SET cashier_Name=?, phone=?, password=?, roleid=? WHERE cashierid=?";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cashier.getCashierName());
            pstmt.setString(2, cashier.getPhone());
            pstmt.setString(3, cashier.getPassword());
            pstmt.setInt(4, cashier.getRoleId());
            pstmt.setInt(5, cashier.getCashierId());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int deleteCashier(int cashierId) {
        String sql = "DELETE FROM cashier WHERE cashierid=?";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, cashierId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Cashier findCashierById(int cashierId) {
        String sql = "SELECT * FROM cashier WHERE cashierid=?";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, cashierId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Cashier cashier = new Cashier();
                    cashier.setCashierId(rs.getInt("cashierid"));
                    cashier.setCashierName(rs.getString("cashier_Name"));
                    cashier.setPhone(rs.getString("phone"));
                    cashier.setPassword(rs.getString("password"));
                    cashier.setRoleId(rs.getInt("roleid"));
                    return cashier;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Cashier> getAllCashiers() {
        List<Cashier> list = new ArrayList<>();
        String sql = "SELECT * FROM cashier";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Cashier cashier = new Cashier();
                cashier.setCashierId(rs.getInt("cashierid"));
                cashier.setCashierName(rs.getString("cashier_Name"));
                cashier.setPhone(rs.getString("phone"));
                cashier.setPassword(rs.getString("password"));
                cashier.setRoleId(rs.getInt("roleid"));
                list.add(cashier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Cashier findCashierByPhone(String phone) {
        String sql = "SELECT * FROM cashier WHERE phone = ?";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, phone);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Cashier cashier = new Cashier();
                    cashier.setCashierId(rs.getInt("cashierid"));
                    cashier.setCashierName(rs.getString("cashier_Name"));
                    cashier.setPhone(rs.getString("phone"));
                    cashier.setPassword(rs.getString("password"));
                    cashier.setRoleId(rs.getInt("roleid"));
                    return cashier;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 其他方法实现省略...
}