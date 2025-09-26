package iuh.fit.se.nguyenphihung_tuan05.bai06.servlet;

import iuh.fit.se.nguyenphihung_tuan05.bai06.model.DanhMuc;
import iuh.fit.se.nguyenphihung_tuan05.bai06.model.TinTuc;
import iuh.fit.se.nguyenphihung_tuan05.bai06.service.DanhSachTinTucQuanLy;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/tintuc-form")
public class TinTucFormServlet extends HttpServlet {
    private DanhSachTinTucQuanLy danhSachTinTucQuanLy;
    @Resource(name = "jdbc/QUANLYDANHMUC")
    private DataSource dataSource;
    @Override
    public void init() throws ServletException {
        try {
            
            danhSachTinTucQuanLy = new DanhSachTinTucQuanLy(dataSource);
        } catch (Exception e) {
            throw new ServletException("Failed to initialize TinTucFormServlet", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            // Lấy danh sách danh mục cho dropdown
            List<DanhMuc> danhSachDanhMuc = danhSachTinTucQuanLy.getAllDanhMuc();
            request.setAttribute("danhSachDanhMuc", danhSachDanhMuc);
            
            request.getRequestDispatcher("/bai06/TinTucForm.jsp").forward(request, response);
            
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi khi tải form: " + e.getMessage());
            request.getRequestDispatcher("/bai06/TinTucForm.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        try {
            String tieuDe = request.getParameter("tieuDe");
            String noiDung = request.getParameter("noiDung");
            String lienKet = request.getParameter("lienKet");
            String maDMParam = request.getParameter("maDM");

            if (tieuDe == null || tieuDe.trim().isEmpty()) {
                throw new IllegalArgumentException("Tiêu đề là bắt buộc nhập");
            }
            if (noiDung == null || noiDung.trim().isEmpty()) {
                throw new IllegalArgumentException("Nội dung là bắt buộc nhập");
            }
            if (lienKet == null || lienKet.trim().isEmpty()) {
                throw new IllegalArgumentException("Liên kết là bắt buộc nhập");
            }
            if (maDMParam == null || maDMParam.trim().isEmpty()) {
                throw new IllegalArgumentException("Danh mục là bắt buộc chọn");
            }

            if (!lienKet.startsWith("http://")) {
                throw new IllegalArgumentException("Liên kết phải bắt đầu bằng 'http://'");
            }

            if (noiDung.length() > 255) {
                throw new IllegalArgumentException("Nội dung không được quá 255 ký tự");
            }
            
            int maDM = Integer.parseInt(maDMParam);

            DanhMuc danhMuc = danhSachTinTucQuanLy.getDanhMucById(maDM);
            if (danhMuc == null) {
                throw new IllegalArgumentException("Danh mục không tồn tại");
            }

            TinTuc tinTuc = new TinTuc();
            tinTuc.setTieuDe(tieuDe.trim());
            tinTuc.setNoiDungTT(noiDung.trim());
            tinTuc.setLienKet(lienKet.trim());
            tinTuc.setDanhMuc(danhMuc);

            danhSachTinTucQuanLy.themTinTuc(tinTuc);

            request.setAttribute("message", "Thêm tin tức thành công!");
            request.setAttribute("tinTuc", tinTuc);
            request.getRequestDispatcher("/bai06/KetQua.jsp").forward(request, response);
            
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Mã danh mục không hợp lệ");
            List<DanhMuc> danhSachDanhMuc = danhSachTinTucQuanLy.getAllDanhMuc();
            request.setAttribute("danhSachDanhMuc", danhSachDanhMuc);
            request.getRequestDispatcher("/bai06/TinTucForm.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            List<DanhMuc> danhSachDanhMuc = danhSachTinTucQuanLy.getAllDanhMuc();
            request.setAttribute("danhSachDanhMuc", danhSachDanhMuc);
            request.getRequestDispatcher("/bai06/TinTucForm.jsp").forward(request, response);
        }
    }
}
