package iuh.fit.se.nguyenphihung_tuan05.bai06.dao;

import iuh.fit.se.nguyenphihung_tuan05.bai06.model.DanhMuc;

import java.util.List;

public interface DanhMucDAO {
    void create(DanhMuc danhMuc);
    void update(DanhMuc danhMuc);
    void delete(int maDM);
    DanhMuc findById(int maDM);
    List<DanhMuc> findAll();
}