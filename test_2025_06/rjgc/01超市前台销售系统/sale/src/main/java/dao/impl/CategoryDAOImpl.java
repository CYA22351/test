package dao.impl;

import dao.CategoryDAO;
import entity.Category;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    @Override
    public List<Category> findAll() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT id, name, parent_id, level, sort_order, status, create_time FROM category ORDER BY sort_order, id";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Category c = new Category();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setParentId(rs.getInt("parent_id"));
                c.setLevel(rs.getInt("level"));
                c.setSortOrder(rs.getInt("sort_order"));
                c.setStatus(rs.getInt("status"));
                c.setCreateTime(rs.getTimestamp("create_time"));
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}