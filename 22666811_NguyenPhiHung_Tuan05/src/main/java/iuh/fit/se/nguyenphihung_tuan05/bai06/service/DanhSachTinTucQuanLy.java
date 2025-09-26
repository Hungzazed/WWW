package iuh.fit.se.nguyenphihung_tuan05.bai06.service;

import iuh.fit.se.nguyenphihung_tuan05.bai06.dao.DanhMucDAO;
import iuh.fit.se.nguyenphihung_tuan05.bai06.dao.TinTucDAO;
import iuh.fit.se.nguyenphihung_tuan05.bai06.dao.Impl.DanhMucDAOImpl;
import iuh.fit.se.nguyenphihung_tuan05.bai06.dao.Impl.TinTucDAOImpl;
import iuh.fit.se.nguyenphihung_tuan05.bai06.model.DanhMuc;
import iuh.fit.se.nguyenphihung_tuan05.bai06.model.TinTuc;

import javax.sql.DataSource;
import java.util.List;

public class DanhSachTinTucQuanLy {
    private final TinTucDAO tinTucDAO;
    private final DanhMucDAO danhMucDAO;

    public DanhSachTinTucQuanLy(DataSource dataSource) {
        this.tinTucDAO = new TinTucDAOImpl(dataSource);
        this.danhMucDAO = new DanhMucDAOImpl(dataSource);
    }

    public List<TinTuc> getTinTucByDanhMuc(int maDM) {
        if (maDM <= 0) {
            throw new IllegalArgumentException("Mã danh mục phải lớn hơn 0");
        }
        return tinTucDAO.findByDanhMuc(maDM);
    }

    public List<TinTuc> getAllTinTuc() {
        return tinTucDAO.findAll();
    }

    public void themTinTuc(TinTuc tinTuc) {
        if (tinTuc == null) {
            throw new IllegalArgumentException("Tin tức không thể null");
        }
        if (tinTuc.getTieuDe() == null || tinTuc.getTieuDe().trim().isEmpty()) {
            throw new IllegalArgumentException("Tiêu đề tin tức không thể trống");
        }
        if (tinTuc.getNoiDungTT() == null || tinTuc.getNoiDungTT().trim().isEmpty()) {
            throw new IllegalArgumentException("Nội dung tin tức không thể trống");
        }
        if (tinTuc.getLienKet() == null || tinTuc.getLienKet().trim().isEmpty()) {
            throw new IllegalArgumentException("Liên kết không thể trống");
        }
        if (!tinTuc.getLienKet().startsWith("http://")) {
            throw new IllegalArgumentException("Liên kết phải bắt đầu bằng 'http://'");
        }
        if (tinTuc.getNoiDungTT().length() > 255) {
            throw new IllegalArgumentException("Nội dung không được quá 255 ký tự");
        }
        if (tinTuc.getDanhMuc() == null || tinTuc.getDanhMuc().getMaDM() <= 0) {
            throw new IllegalArgumentException("Danh mục tin tức không hợp lệ");
        }

        tinTucDAO.create(tinTuc);
    }

    public void xoaTinTuc(int maTT) {
        if (maTT <= 0) {
            throw new IllegalArgumentException("Mã tin tức phải lớn hơn 0");
        }
        TinTuc existingTinTuc = tinTucDAO.findById(maTT);
        if (existingTinTuc == null) {
            throw new IllegalArgumentException("Tin tức không tồn tại");
        }
        tinTucDAO.delete(maTT);
    }

    public TinTuc getTinTucById(int maTT) {
        if (maTT <= 0) {
            throw new IllegalArgumentException("Mã tin tức phải lớn hơn 0");
        }
        return tinTucDAO.findById(maTT);
    }

    public void capNhatTinTuc(TinTuc tinTuc) {
        if (tinTuc == null || tinTuc.getMaTT() <= 0) {
            throw new IllegalArgumentException("Mã tin tức phải lớn hơn 0");
        }
        if (tinTuc.getTieuDe() == null || tinTuc.getTieuDe().trim().isEmpty()) {
            throw new IllegalArgumentException("Tiêu đề tin tức không thể trống");
        }
        if (tinTuc.getNoiDungTT() == null || tinTuc.getNoiDungTT().trim().isEmpty()) {
            throw new IllegalArgumentException("Nội dung tin tức không thể trống");
        }
        if (tinTuc.getLienKet() == null || tinTuc.getLienKet().trim().isEmpty()) {
            throw new IllegalArgumentException("Liên kết không thể trống");
        }
        if (!tinTuc.getLienKet().startsWith("http://")) {
            throw new IllegalArgumentException("Liên kết phải bắt đầu bằng 'http://'");
        }
        if (tinTuc.getNoiDungTT().length() > 255) {
            throw new IllegalArgumentException("Nội dung không được quá 255 ký tự");
        }
        if (tinTuc.getDanhMuc() == null || tinTuc.getDanhMuc().getMaDM() <= 0) {
            throw new IllegalArgumentException("Danh mục tin tức không hợp lệ");
        }
        tinTucDAO.update(tinTuc);
    }

    public List<DanhMuc> getAllDanhMuc() {
        return danhMucDAO.findAll();
    }

    public DanhMuc getDanhMucById(int maDM) {
        if (maDM <= 0) {
            throw new IllegalArgumentException("Mã danh mục phải lớn hơn 0");
        }
        return danhMucDAO.findById(maDM);
    }
}