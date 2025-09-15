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
}