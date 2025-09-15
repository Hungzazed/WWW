package iuh.fit.se.nguyenphihung_tuan03_04.bai03.servlet;

import iuh.fit.se.nguyenphihung_tuan03_04.bai03.beans.Product;
import iuh.fit.se.nguyenphihung_tuan03_04.bai03.dao.ProductDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
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
        String idStr = req.getParameter("id");
        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            Product product = productDAO.getProductById(id);
            if (product != null) {
                req.setAttribute("product", product);
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/bai03/product-details.jsp");
                requestDispatcher.forward(req, resp);
            }
        }
        List<Product> products = productDAO.getAllProducts();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/bai03/products-list.jsp").forward(req, resp);
    }
}
