package iuh.fit.se.nguyenphihung_tuan05.bai05.servlet;

import iuh.fit.se.nguyenphihung_tuan05.bai05.dao.DepartmentDAO;
import iuh.fit.se.nguyenphihung_tuan05.bai05.dao.EmployeeDAO;
import iuh.fit.se.nguyenphihung_tuan05.bai05.dao.Impl.DepartmentDAOImpl;
import iuh.fit.se.nguyenphihung_tuan05.bai05.dao.Impl.EmployeeDAOImpl;
import iuh.fit.se.nguyenphihung_tuan05.bai05.model.Employee;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {

    @Resource(name = "jdbc/hrdb")
    private DataSource dataSource;

    private EmployeeDAO empDao;
    private DepartmentDAO deptDao;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            empDao = new EmployeeDAOImpl(dataSource);
            deptDao = new DepartmentDAOImpl(dataSource);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list": {
                List<Employee> allEmployees = empDao.findAll();
                req.setAttribute("employees", allEmployees);
                req.setAttribute("departments", deptDao.findAll());
                req.getRequestDispatcher("employee-list.jsp").forward(req, resp);
                break;
            }

            case "new": {
                req.setAttribute("departments", deptDao.findAll());
                req.getRequestDispatcher("employee-form.jsp").forward(req, resp);
                break;
            }

            case "edit": {
                int id = Integer.parseInt(req.getParameter("id"));
                Employee emp = empDao.findById(id);
                req.setAttribute("employee", emp);
                req.setAttribute("departments", deptDao.findAll());
                req.getRequestDispatcher("employee-form.jsp").forward(req, resp);
                break;
            }

            case "delete": {
                int id = Integer.parseInt(req.getParameter("id"));
                empDao.delete(id);
                resp.sendRedirect("employees");
                break;
            }

            case "viewbyid": {
                String deptId = req.getParameter("deptId");
                List<Employee> list;

                if (deptId != null && !deptId.isEmpty()) {
                    list = empDao.findByDepartment(Integer.parseInt(deptId));
                } else {
                    list = empDao.findByDepartment(1);
                }

                req.setAttribute("employees", list);
                req.setAttribute("departments", deptDao.findAll());
                req.getRequestDispatcher("employee-list.jsp").forward(req, resp);
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idParam = req.getParameter("id");
        int id = (idParam != null && !idParam.isEmpty()) ? Integer.parseInt(idParam) : 0;

        String name = req.getParameter("name");
        double salary = Double.parseDouble(req.getParameter("salary"));
        int deptId = Integer.parseInt(req.getParameter("departmentId"));

        Employee emp = new Employee(id, name, salary, deptId);

        if (id > 0) {
            empDao.update(emp);
        } else {
            empDao.create(emp);
        }

        // redirect lại về employees theo departmentId
        resp.sendRedirect("employees?action=list");
    }
}
