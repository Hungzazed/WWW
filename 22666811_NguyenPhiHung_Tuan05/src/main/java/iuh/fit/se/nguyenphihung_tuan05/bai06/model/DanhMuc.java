package iuh.fit.se.nguyenphihung_tuan05.bai06.model;

import lombok.Data;

@Data
public class DanhMuc {
    private int maDM;
    private String tenDanhMuc;
    private String nguoiQuanLy;
    private String ghiChu;

    public DanhMuc() {
    }

    public DanhMuc(int maDM, String tenDanhMuc, String nguoiQuanLy, String ghiChu) {
        this.maDM = maDM;
        this.tenDanhMuc = tenDanhMuc;
        this.nguoiQuanLy = nguoiQuanLy;
        this.ghiChu = ghiChu;
    }

    public DanhMuc(String tenDanhMuc, String nguoiQuanLy, String ghiChu) {
        this.tenDanhMuc = tenDanhMuc;
        this.nguoiQuanLy = nguoiQuanLy;
        this.ghiChu = ghiChu;
    }
}
