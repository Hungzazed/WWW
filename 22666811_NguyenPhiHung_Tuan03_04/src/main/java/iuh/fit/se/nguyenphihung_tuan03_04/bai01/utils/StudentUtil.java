package iuh.fit.se.nguyenphihung_tuan03_04.bai01.utils;

import iuh.fit.se.nguyenphihung_tuan03_04.bai01.model.Student;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentUtil {
    private DataSource dataSource;

    public StudentUtil(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Lấy danh sách sinh viên
    public List<Student> getStudents() throws Exception {
        List<Student> students = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            String sql = "SELECT * FROM students ORDER BY id";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String dob = rs.getString("dob");
                String email = rs.getString("email");
                String mobile = rs.getString("mobile");
                String gender = rs.getString("gender");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String pinCode = rs.getString("pin_code");
                String state = rs.getString("state");
                String country = rs.getString("country");
                String hobbiesStr = rs.getString("hobbies");
                String boardX = rs.getString("board_x");
                String percentageX = rs.getString("percentage_x");
                String yearX = rs.getString("year_x");
                String boardXII = rs.getString("board_xii");
                String percentageXII = rs.getString("percentage_xii");
                String yearXII = rs.getString("year_xii");
                String course = rs.getString("course");

                List<String> hobbies = new ArrayList<>();

                if (hobbiesStr != null && !hobbiesStr.trim().isEmpty()) {
                    hobbies = Arrays.asList(hobbiesStr.split(","));
                }

                Student student = new Student(firstName, lastName, dob, email, mobile,
                        gender, address, city, pinCode, state, country,
                        hobbies, boardX, percentageX, yearX,
                        boardXII, percentageXII, yearXII, course);

                students.add(student);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(conn, stmt, rs);
        }

        return students;
    }

    // Thêm sinh viên mới
    public void addStudent(Student student) throws Exception {
        String sql = "INSERT INTO students (first_name, last_name, dob, email, mobile, " +
                "gender, address, city, pin_code, state, country, hobbies, " +
                "board_x, percentage_x, year_x, board_xii, percentage_xii, year_xii, course) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getDob());
            ps.setString(4, student.getEmail());
            ps.setString(5, student.getMobile());
            ps.setString(6, student.getGender());
            ps.setString(7, student.getAddress());
            ps.setString(8, student.getCity());
            ps.setString(9, student.getPinCode());
            ps.setString(10, student.getState());
            ps.setString(11, student.getCountry());
            String hobbies = String.join(",", student.getHobbies());
            ps.setString(12, hobbies);
            ps.setString(13, student.getBoardX());
            ps.setString(14, student.getPercentageX());
            ps.setString(15, student.getYearX());
            ps.setString(16, student.getBoardXII());
            ps.setString(17, student.getPercentageXII());
            ps.setString(18, student.getYearXII());
            ps.setString(19, student.getCourse());

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
