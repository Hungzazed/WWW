package iuh.fit.se.nguyenphihung_tuan03_04.bai04.util;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBUtil {
    private DataSource dataSource;
    public DBUtil(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public Connection getConnection() {
        Connection con;
        try  {
            con = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
}