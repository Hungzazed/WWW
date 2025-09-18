package iuh.fit.se.nguyenphihung_tuan03_04.bai04.servlet;

import iuh.fit.se.nguyenphihung_tuan03_04.bai04.beans.CartBean;
import iuh.fit.se.nguyenphihung_tuan03_04.bai04.dao.BookDAO;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/checkouts")
public class CheckoutServlet extends HttpServlet {

    private BookDAO bookDAO;
    @Resource(name = "jdbc/shopdb")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        bookDAO = new BookDAO(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        CartBean cart = (CartBean) session.getAttribute("bookcart");
        if(cart == null){
            cart = new CartBean();
            session.setAttribute("bookcart", cart);
        }
        if(cart.getItems().isEmpty()){
            session.setAttribute("message", "Giỏ hàng trống");
            resp.sendRedirect("/books");
            return;
        }
        req.getRequestDispatcher("/bai04/checkout-form.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        CartBean cart = (CartBean) session.getAttribute("bookcart");
        if(cart != null){
            cart.getItems()
                    .forEach(item -> {
                        try {
                            bookDAO.updateQuantity(item.getBook().getId(), item.getQuantity());
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    });
            cart.clear();
            session.setAttribute("message", "Thanh toán thành công!");

//            resp.sendRedirect("books");
//            return;
        }
        resp.sendRedirect(req.getContextPath() + "/books");
    }
}