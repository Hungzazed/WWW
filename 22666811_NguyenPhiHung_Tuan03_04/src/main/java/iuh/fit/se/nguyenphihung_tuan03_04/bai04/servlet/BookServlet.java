package iuh.fit.se.nguyenphihung_tuan03_04.bai04.servlet;

import iuh.fit.se.nguyenphihung_tuan03_04.bai04.beans.Book;
import iuh.fit.se.nguyenphihung_tuan03_04.bai04.dao.BookDAO;
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

@WebServlet("/books")
public class BookServlet extends HttpServlet {
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
        String idStr = req.getParameter("id");
        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            Book book = bookDAO.getBookById(id);
            if (book != null) {
                req.setAttribute("book", book);
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/bai04/book-details.jsp");
                requestDispatcher.forward(req, resp);
            }
        }
        List<Book> books = bookDAO.getAllBooks();
        req.setAttribute("books", books);
        req.getRequestDispatcher("/bai04/books-list.jsp").forward(req, resp);
    }
}