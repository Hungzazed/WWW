package iuh.fit.se.nguyenphihung_tuan05.bai06.model;

import lombok.Data;

@Data
public class TinTuc {
    private int maTT;
    private String tieuDe;
    private String noiDungTT;
    private String lienKet;
    private DanhMuc danhMuc;

    public TinTuc() {
    }

    public TinTuc(int maTT, String tieuDe, String noiDungTT, String lienKet, DanhMuc danhMuc) {
        this.maTT = maTT;
        this.tieuDe = tieuDe;
        this.noiDungTT = noiDungTT;
        this.lienKet = lienKet;
        this.danhMuc = danhMuc;
    }

    public TinTuc(String tieuDe, String noiDungTT, String lienKet, DanhMuc danhMuc) {
        this.tieuDe = tieuDe;
        this.noiDungTT = noiDungTT;
        this.lienKet = lienKet;
        this.danhMuc = danhMuc;
    }
}
