package iuh.fit.se.nguyenphihung_tuan05.bai06.dao.Impl;

import iuh.fit.se.nguyenphihung_tuan05.bai06.dao.DanhMucDAO;
import iuh.fit.se.nguyenphihung_tuan05.bai06.model.DanhMuc;
import iuh.fit.se.nguyenphihung_tuan05.bai06.utils.DBUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DanhMucDAOImpl implements DanhMucDAO {
    private final DBUtil dbUtil;

    public DanhMucDAOImpl(DataSource dataSource) {
        dbUtil = new DBUtil(dataSource);
    }

    @Override
    public void create(DanhMuc danhMuc) {
        String sql = "INSERT INTO DANHMUC (TENDANHMUC, NGUOIQUANLY, GHICHU) VALUES (?, ?, ?)";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, danhMuc.getTenDanhMuc());
            stmt.setString(2, danhMuc.getNguoiQuanLy());
            stmt.setString(3, danhMuc.getGhiChu());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating danh muc", e);
        }
    }

    @Override
    public void update(DanhMuc danhMuc) {
        String sql = "UPDATE DANHMUC SET TENDANHMUC = ?, NGUOIQUANLY = ?, GHICHU = ? WHERE MADM = ?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, danhMuc.getTenDanhMuc());
            stmt.setString(2, danhMuc.getNguoiQuanLy());
            stmt.setString(3, danhMuc.getGhiChu());
            stmt.setInt(4, danhMuc.getMaDM());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating danh muc", e);
        }
    }

    @Override
    public void delete(int maDM) {
        String sql = "DELETE FROM DANHMUC WHERE MADM = ?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, maDM);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting danh muc", e);
        }
    }

    @Override
    public DanhMuc findById(int maDM) {
        String sql = "SELECT MADM, TENDANHMUC, NGUOIQUANLY, GHICHU FROM DANHMUC WHERE MADM = ?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, maDM);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    DanhMuc danhMuc = new DanhMuc();
                    danhMuc.setMaDM(rs.getInt("MADM"));
                    danhMuc.setTenDanhMuc(rs.getString("TENDANHMUC"));
                    danhMuc.setNguoiQuanLy(rs.getString("NGUOIQUANLY"));
                    danhMuc.setGhiChu(rs.getString("GHICHU"));
                    return danhMuc;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding danh muc by id", e);
        }
        return null;
    }

    @Override
    public List<DanhMuc> findAll() {
        String sql = "SELECT MADM, TENDANHMUC, NGUOIQUANLY, GHICHU FROM DANHMUC ORDER BY TENDANHMUC";
        List<DanhMuc> danhMucs = new ArrayList<>();

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                DanhMuc danhMuc = new DanhMuc();
                danhMuc.setMaDM(rs.getInt("MADM"));
                danhMuc.setTenDanhMuc(rs.getString("TENDANHMUC"));
                danhMuc.setNguoiQuanLy(rs.getString("NGUOIQUANLY"));
                danhMuc.setGhiChu(rs.getString("GHICHU"));
                danhMucs.add(danhMuc);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding all danh muc", e);
        }

        return danhMucs;
    }
}