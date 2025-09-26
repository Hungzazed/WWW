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

@WebServlet("/bookcart")
public class CartServlet extends HttpServlet {
    private BookDAO bookDAO;
    @Resource(name = "jdbc/shopdb")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();
        bookDAO = new BookDAO(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/bai04/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        CartBean cart = (CartBean) session.getAttribute("bookcart");
        if (cart == null) {
            cart = new CartBean();
            session.setAttribute("bookcart", cart);
        }
        String action = req.getParameter("action");
        try {
            int bookId = Integer.parseInt(req.getParameter("id"));

            switch (action) {
                case "add":
                    cart.addBook(bookDAO.getBookById(bookId));
                    break;
                case "update":
                    int quantity = Integer.parseInt(req.getParameter("quantity"));
                    cart.updateQuantity(bookId, quantity);
                    break;
                case "remove":
                    cart.deleteBook(bookId);
                    break;
                case "clear":
                    cart.clear();
                    break;
                default:
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid book ID or quantity");
        }
        resp.sendRedirect("bookcart");
    }
}