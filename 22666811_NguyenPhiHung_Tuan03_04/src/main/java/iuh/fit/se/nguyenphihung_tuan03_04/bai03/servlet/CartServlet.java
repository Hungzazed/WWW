package iuh.fit.se.nguyenphihung_tuan03_04.bai03.servlet;

import iuh.fit.se.nguyenphihung_tuan03_04.bai03.beans.CartBean;
import iuh.fit.se.nguyenphihung_tuan03_04.bai03.dao.ProductDAO;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private ProductDAO productDAO;
    @Resource(name = "jdbc/shopdb")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();
        productDAO = new ProductDAO(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/bai03/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        CartBean cart = (CartBean) session.getAttribute("cart");
        if (cart == null) {
            cart = new CartBean();
            session.setAttribute("cart", cart);
        }
        String action = req.getParameter("action");
        try {
            int productId = Integer.parseInt(req.getParameter("id"));

            switch (action) {
                case "add":
                    cart.addProduct(productDAO.getProductById(productId));
                    break;
                case "update":
                    int quantity = Integer.parseInt(req.getParameter("quantity"));
                    cart.updateQuantity(productId, quantity);
                    break;
                case "remove":
                    cart.deleteProduct(productId);
                    break;
                case "clear":
                    cart.clear();
                    break;
                default:
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product ID or quantity");
        }
        resp.sendRedirect("cart");
    }
}
