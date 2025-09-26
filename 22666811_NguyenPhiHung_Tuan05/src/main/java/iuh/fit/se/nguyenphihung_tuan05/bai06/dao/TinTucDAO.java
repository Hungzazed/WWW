package iuh.fit.se.nguyenphihung_tuan05.bai06.dao;

import iuh.fit.se.nguyenphihung_tuan05.bai06.model.TinTuc;

import java.util.List;

public interface TinTucDAO {
    void create(TinTuc tinTuc);
    void update(TinTuc tinTuc);
    void delete(int maTT);
    TinTuc findById(int maTT);
    List<TinTuc> findAll();
    List<TinTuc> findByDanhMuc(int maDM);
}