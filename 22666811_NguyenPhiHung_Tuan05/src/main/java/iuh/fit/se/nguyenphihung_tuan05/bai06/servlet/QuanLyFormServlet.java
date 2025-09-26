package iuh.fit.se.nguyenphihung_tuan05.bai06.servlet;

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

@WebServlet("/quan-ly")
public class QuanLyFormServlet extends HttpServlet {
    private DanhSachTinTucQuanLy danhSachTinTucQuanLy;
    @Resource(name = "jdbc/QUANLYDANHMUC")
    private DataSource dataSource;
    @Override
    public void init() throws ServletException {
        try {
            danhSachTinTucQuanLy = new DanhSachTinTucQuanLy(dataSource);
        } catch (Exception e) {
            throw new ServletException("Failed to initialize QuanLyFormServlet", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action == null || action.equals("list")) {
                List<TinTuc> danhSachTinTuc = danhSachTinTucQuanLy.getAllTinTuc();
                request.setAttribute("danhSachTinTuc", danhSachTinTuc);
                request.getRequestDispatcher("/bai06/QuanLyForm.jsp").forward(request, response);
                
            } else if (action.equals("delete")) {
                String maTTParam = request.getParameter("maTT");
                if (maTTParam != null) {
                    int maTT = Integer.parseInt(maTTParam);
                    TinTuc tinTuc = danhSachTinTucQuanLy.getTinTucById(maTT);
                    if (tinTuc != null) {
                        danhSachTinTucQuanLy.xoaTinTuc(maTT);
                        request.setAttribute("message", "Xóa tin tức thành công: " + tinTuc.getTieuDe());
                    } else {
                        request.setAttribute("error", "Tin tức không tồn tại");
                    }
                }

                List<TinTuc> danhSachTinTuc = danhSachTinTucQuanLy.getAllTinTuc();
                request.setAttribute("danhSachTinTuc", danhSachTinTuc);
                request.getRequestDispatcher("/bai06/QuanLyForm.jsp").forward(request, response);
                
            } else {
                response.sendRedirect(request.getContextPath() + "/quan-ly?action=list");
            }
            
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Mã tin tức không hợp lệ");
            List<TinTuc> danhSachTinTuc = danhSachTinTucQuanLy.getAllTinTuc();
            request.setAttribute("danhSachTinTuc", danhSachTinTuc);
            request.getRequestDispatcher("/bai06/QuanLyForm.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi: " + e.getMessage());
            List<TinTuc> danhSachTinTuc = danhSachTinTucQuanLy.getAllTinTuc();
            request.setAttribute("danhSachTinTuc", danhSachTinTuc);
            request.getRequestDispatcher("/bai06/QuanLyForm.jsp").forward(request, response);
        }
    }
}