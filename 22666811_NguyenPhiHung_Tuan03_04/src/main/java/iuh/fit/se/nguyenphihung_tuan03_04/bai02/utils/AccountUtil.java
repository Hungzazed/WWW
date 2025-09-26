package iuh.fit.se.nguyenphihung_tuan03_04.bai02.utils;

import iuh.fit.se.nguyenphihung_tuan03_04.bai02.model.Account;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccountUtil {
    private DataSource dataSource;

    public AccountUtil(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Lấy danh sách tài khoản
    public List<Account> getAccounts() throws Exception {
        List<Account> accounts = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            String sql = "SELECT * FROM accounts ORDER BY id";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                Date dobDate = rs.getDate("date_of_birth");
                LocalDate dateOfBirth = dobDate != null ? dobDate.toLocalDate() : null;

                Account account = new Account(id, firstName, lastName, email, password, dateOfBirth);
                accounts.add(account);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(conn, stmt, rs);
        }

        return accounts;
    }

    // Thêm tài khoản mới
    public void addAccount(Account account) throws Exception {
        String sql = "INSERT INTO accounts (first_name, last_name, email, password, date_of_birth) " +
                "VALUES (?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, account.getFirstName());
            ps.setString(2, account.getLastName());
            ps.setString(3, account.getEmail());
            ps.setString(4, account.getPassword());
            ps.setDate(5, account.getDateOfBirth() != null ? Date.valueOf(account.getDateOfBirth()) : null);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(conn, ps, null);
        }
    }

    // Tìm tài khoản theo email
    public Account findAccountByEmail(String email) throws Exception {
        Account account = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            String sql = "SELECT * FROM accounts WHERE email = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String password = rs.getString("password");
                Date dobDate = rs.getDate("date_of_birth");
                LocalDate dateOfBirth = dobDate != null ? dobDate.toLocalDate() : null;

                account = new Account(id, firstName, lastName, email, password, dateOfBirth);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(conn, ps, rs);
        }

        return account;
    }

    // Cập nhật tài khoản
    public void updateAccount(Account account) throws Exception {
        String sql = "UPDATE accounts SET first_name = ?, last_name = ?, email = ?, password = ?, date_of_birth = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, account.getFirstName());
            ps.setString(2, account.getLastName());
            ps.setString(3, account.getEmail());
            ps.setString(4, account.getPassword());
            ps.setDate(5, account.getDateOfBirth() != null ? Date.valueOf(account.getDateOfBirth()) : null);
            ps.setInt(6, account.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(conn, ps, null);
        }
    }

    // Xóa tài khoản
    public void deleteAccount(int id) throws Exception {
        String sql = "DELETE FROM accounts WHERE id = ?";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(conn, ps, null);
        }
    }

    // Đóng kết nối
    private void close(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
        } catch (Exception ignored) {}
        try {
            if (stmt != null) stmt.close();
        } catch (Exception ignored) {}
        try {
            if (conn != null) conn.close();
        } catch (Exception ignored) {}
    }
}