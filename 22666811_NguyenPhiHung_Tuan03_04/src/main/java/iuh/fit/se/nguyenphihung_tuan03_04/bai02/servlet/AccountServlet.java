package iuh.fit.se.nguyenphihung_tuan03_04.bai02.servlet;

import iuh.fit.se.nguyenphihung_tuan03_04.bai02.model.Account;
import iuh.fit.se.nguyenphihung_tuan03_04.bai02.utils.AccountUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/account_servlet")
public class AccountServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private AccountUtil accountUtil;
    
    @Resource(name = "jdbc/accountdb")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();
        accountUtil = new AccountUtil(dataSource);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String dateOfBirthStr = request.getParameter("dateOfBirth");

        // Parse date of birth
        LocalDate dateOfBirth = null;
        if (dateOfBirthStr != null && !dateOfBirthStr.trim().isEmpty()) {
            try {
                dateOfBirth = LocalDate.parse(dateOfBirthStr);
            } catch (Exception e) {
                // Log error or handle invalid date format
                System.err.println("Invalid date format: " + dateOfBirthStr);
            }
        }

        // Create Account object
        Account account = new Account(firstName, lastName, email, password, dateOfBirth);

        try {
            accountUtil.addAccount(account);
        } catch (Exception e) {
            throw new RuntimeException("Error adding account", e);
        }

        // Get all accounts to display
        List<Account> accounts = null;
        try {
            accounts = accountUtil.getAccounts();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving accounts", e);
        }

        request.setAttribute("accounts", accounts);
        request.getRequestDispatcher("bai02/account-result.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Handle GET request - display form or list accounts
        String action = request.getParameter("action");
        
        if ("list".equals(action)) {
            // List all accounts
            try {
                List<Account> accounts = accountUtil.getAccounts();
                request.setAttribute("accounts", accounts);
                request.getRequestDispatcher("bai02/account-result.jsp").forward(request, response);
            } catch (Exception e) {
                throw new RuntimeException("Error retrieving accounts", e);
            }
        } else {
            // Display form
            request.getRequestDispatcher("bai02/accountForm.jsp").forward(request, response);
        }
    }
}