package iuh.fit.se.nguyenphihung_tuan05.bai06.dao.Impl;

import iuh.fit.se.nguyenphihung_tuan05.bai06.dao.TinTucDAO;
import iuh.fit.se.nguyenphihung_tuan05.bai06.model.DanhMuc;
import iuh.fit.se.nguyenphihung_tuan05.bai06.model.TinTuc;
import iuh.fit.se.nguyenphihung_tuan05.bai06.utils.DBUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TinTucDAOImpl implements TinTucDAO {
    private final DBUtil dbUtil;

    public TinTucDAOImpl(DataSource dataSource) {
        dbUtil = new DBUtil(dataSource);
    }

    @Override
    public void create(TinTuc tinTuc) {
        String sql = "INSERT INTO TINTUC (TIEUDE, NOIDUNGTT, LIENKET, MADM) VALUES (?, ?, ?, ?)";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tinTuc.getTieuDe());
            stmt.setString(2, tinTuc.getNoiDungTT());
            stmt.setString(3, tinTuc.getLienKet());
            stmt.setInt(4, tinTuc.getDanhMuc().getMaDM());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating tin tuc", e);
        }
    }

    @Override
    public void update(TinTuc tinTuc) {
        String sql = "UPDATE TINTUC SET TIEUDE = ?, NOIDUNGTT = ?, LIENKET = ?, MADM = ? WHERE MATT = ?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tinTuc.getTieuDe());
            stmt.setString(2, tinTuc.getNoiDungTT());
            stmt.setString(3, tinTuc.getLienKet());
            stmt.setInt(4, tinTuc.getDanhMuc().getMaDM());
            stmt.setInt(5, tinTuc.getMaTT());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating tin tuc", e);
        }
    }

    @Override
    public void delete(int maTT) {
        String sql = "DELETE FROM TINTUC WHERE MATT = ?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, maTT);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting tin tuc", e);
        }
    }

    @Override
    public TinTuc findById(int maTT) {
        String sql = "SELECT t.MATT, t.TIEUDE, t.NOIDUNGTT, t.LIENKET, t.MADM, " +
                    "d.TENDANHMUC, d.NGUOIQUANLY, d.GHICHU " +
                    "FROM TINTUC t LEFT JOIN DANHMUC d ON t.MADM = d.MADM " +
                    "WHERE t.MATT = ?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, maTT);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    TinTuc tinTuc = new TinTuc();
                    tinTuc.setMaTT(rs.getInt("MATT"));
                    tinTuc.setTieuDe(rs.getString("TIEUDE"));
                    tinTuc.setNoiDungTT(rs.getString("NOIDUNGTT"));
                    tinTuc.setLienKet(rs.getString("LIENKET"));

                    // Set danh muc if exists
                    if (rs.getInt("MADM") > 0) {
                        DanhMuc danhMuc = new DanhMuc();
                        danhMuc.setMaDM(rs.getInt("MADM"));
                        danhMuc.setTenDanhMuc(rs.getString("TENDANHMUC"));
                        danhMuc.setNguoiQuanLy(rs.getString("NGUOIQUANLY"));
                        danhMuc.setGhiChu(rs.getString("GHICHU"));
                        tinTuc.setDanhMuc(danhMuc);
                    }

                    return tinTuc;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding tin tuc by id", e);
        }
        return null;
    }

    @Override
    public List<TinTuc> findAll() {
        String sql = "SELECT t.MATT, t.TIEUDE, t.NOIDUNGTT, t.LIENKET, t.MADM, " +
                    "d.TENDANHMUC, d.NGUOIQUANLY, d.GHICHU " +
                    "FROM TINTUC t LEFT JOIN DANHMUC d ON t.MADM = d.MADM " +
                    "ORDER BY t.MATT DESC";
        List<TinTuc> tinTucs = new ArrayList<>();

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TinTuc tinTuc = new TinTuc();
                tinTuc.setMaTT(rs.getInt("MATT"));
                tinTuc.setTieuDe(rs.getString("TIEUDE"));
                tinTuc.setNoiDungTT(rs.getString("NOIDUNGTT"));
                tinTuc.setLienKet(rs.getString("LIENKET"));

                // Set danh muc if exists
                if (rs.getInt("MADM") > 0) {
                    DanhMuc danhMuc = new DanhMuc();
                    danhMuc.setMaDM(rs.getInt("MADM"));
                    danhMuc.setTenDanhMuc(rs.getString("TENDANHMUC"));
                    danhMuc.setNguoiQuanLy(rs.getString("NGUOIQUANLY"));
                    danhMuc.setGhiChu(rs.getString("GHICHU"));
                    tinTuc.setDanhMuc(danhMuc);
                }

                tinTucs.add(tinTuc);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding all tin tuc", e);
        }

        return tinTucs;
    }

    @Override
    public List<TinTuc> findByDanhMuc(int maDM) {
        String sql = "SELECT t.MATT, t.TIEUDE, t.NOIDUNGTT, t.LIENKET, t.MADM, " +
                    "d.TENDANHMUC, d.NGUOIQUANLY, d.GHICHU " +
                    "FROM TINTUC t LEFT JOIN DANHMUC d ON t.MADM = d.MADM " +
                    "WHERE t.MADM = ? ORDER BY t.MATT DESC";
        List<TinTuc> tinTucs = new ArrayList<>();

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, maDM);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    TinTuc tinTuc = new TinTuc();
                    tinTuc.setMaTT(rs.getInt("MATT"));
                    tinTuc.setTieuDe(rs.getString("TIEUDE"));
                    tinTuc.setNoiDungTT(rs.getString("NOIDUNGTT"));
                    tinTuc.setLienKet(rs.getString("LIENKET"));

                    // Set danh muc
                    DanhMuc danhMuc = new DanhMuc();
                    danhMuc.setMaDM(rs.getInt("MADM"));
                    danhMuc.setTenDanhMuc(rs.getString("TENDANHMUC"));
                    danhMuc.setNguoiQuanLy(rs.getString("NGUOIQUANLY"));
                    danhMuc.setGhiChu(rs.getString("GHICHU"));
                    tinTuc.setDanhMuc(danhMuc);

                    tinTucs.add(tinTuc);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding tin tuc by danh muc", e);
        }

        return tinTucs;
    }
}