package iuh.fit.se.nguyenphihung_tuan03_04.bai03.dao;

import iuh.fit.se.nguyenphihung_tuan03_04.bai03.beans.Product;
import iuh.fit.se.nguyenphihung_tuan03_04.bai03.util.DBUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private DBUtil dbUtil;

    public ProductDAO(DataSource dataSource) {
        this.dbUtil = new DBUtil(dataSource);
    }

    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Connection conn = dbUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String model = rs.getString("model");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String image = rs.getString("imgurl");
                String description = rs.getString("description");
                Product p = new Product(id, model, description, quantity, price, image);
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public Product getProductById(int id) {
        String sql = "SELECT * FROM products where id = ?";
        try (Connection conn = dbUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String model = rs.getString("model");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String image = rs.getString("imgurl");
                String description = rs.getString("description");
                return new Product(id, model, description, quantity, price, image);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
