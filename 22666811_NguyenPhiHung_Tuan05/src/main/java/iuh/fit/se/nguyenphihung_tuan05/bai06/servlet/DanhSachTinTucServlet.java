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

@WebServlet("/danhsach-tintuc")
public class DanhSachTinTucServlet extends HttpServlet {
    private DanhSachTinTucQuanLy danhSachTinTucQuanLy;
    @Resource(name = "jdbc/QUANLYDANHMUC")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            danhSachTinTucQuanLy = new DanhSachTinTucQuanLy(dataSource);
        } catch (Exception e) {
            throw new ServletException("Failed to initialize DanhSachTinTucServlet", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String danhMucParam = request.getParameter("danhMuc");
        try {
            List<TinTuc> danhSachTinTuc;
            DanhMuc selectedDanhMuc = null;
            
            if (danhMucParam != null && !danhMucParam.isEmpty()) {
                int maDM = Integer.parseInt(danhMucParam);
                danhSachTinTuc = danhSachTinTucQuanLy.getTinTucByDanhMuc(maDM);
                selectedDanhMuc = danhSachTinTucQuanLy.getDanhMucById(maDM);
            } else {
                danhSachTinTuc = danhSachTinTucQuanLy.getAllTinTuc();
            }

            List<DanhMuc> danhSachDanhMuc = danhSachTinTucQuanLy.getAllDanhMuc();
            request.setAttribute("danhSachTinTuc", danhSachTinTuc);
            request.setAttribute("danhSachDanhMuc", danhSachDanhMuc);
            request.setAttribute("selectedDanhMuc", selectedDanhMuc);
            request.setAttribute("selectedDanhMucId", danhMucParam);
            
            request.getRequestDispatcher("/bai06/DanhSachTinTuc.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Mã danh mục không hợp lệ");
            request.getRequestDispatcher("/bai06/DanhSachTinTuc.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi: " + e.getMessage());
            request.getRequestDispatcher("/bai06/DanhSachTinTuc.jsp").forward(request, response);
        }
    }
}
