package iuh.fit.se.nguyenphihung_tuan03_04.bai04.dao;

import iuh.fit.se.nguyenphihung_tuan03_04.bai04.beans.Book;
import iuh.fit.se.nguyenphihung_tuan03_04.bai04.util.DBUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private DBUtil dbUtil;

    public BookDAO(DataSource dataSource) {
        this.dbUtil = new DBUtil(dataSource);
    }

    public List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Connection conn = dbUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String imgUrl = rs.getString("imgUrl");
                Book book = new Book(id, title, author, price, quantity, imgUrl);
                list.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public Book getBookById(int id) {
        String sql = "SELECT * FROM books where id = ?";
        try (Connection conn = dbUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String imgUrl = rs.getString("imgUrl");
                return new Book(id, title, author, price, quantity, imgUrl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateQuantity(int id, int newQuantity) {
        String sql = "UPDATE books SET quantity = ? WHERE id = ?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, newQuantity);
            stmt.setInt(2, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Book> getBooksByFilter(String search, String priceFilter) {
        List<Book> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM books WHERE 1=1");

        if (search != null && !search.trim().isEmpty()) {
            sql.append(" AND LOWER(title) LIKE ?");
        }
        if (priceFilter != null) {
            switch (priceFilter) {
                case "under50k":
                    sql.append(" AND price < 50000");
                    break;
                case "50kto100k":
                    sql.append(" AND price BETWEEN 50000 AND 100000");
                    break;
                case "above100k":
                    sql.append(" AND price > 100000");
                    break;
                default:
                    sql.append(" AND 1 = 1");
                    break;
            }
        }

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            if (search != null && !search.trim().isEmpty()) {
                stmt.setString(1, "%" + search.toLowerCase() + "%");
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String imgUrl = rs.getString("imgUrl");

                Book book = new Book(id, title, author, price, quantity, imgUrl);
                list.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}