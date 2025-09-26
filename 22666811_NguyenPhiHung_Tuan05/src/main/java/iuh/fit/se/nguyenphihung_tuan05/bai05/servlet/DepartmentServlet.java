package iuh.fit.se.nguyenphihung_tuan05.bai05.servlet;

import iuh.fit.se.nguyenphihung_tuan05.bai05.dao.DepartmentDAO;
import iuh.fit.se.nguyenphihung_tuan05.bai05.dao.Impl.DepartmentDAOImpl;
import iuh.fit.se.nguyenphihung_tuan05.bai05.model.Department;
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

@WebServlet("/departments")
public class DepartmentServlet extends HttpServlet {
    @Resource(name = "jdbc/hrdb")
    private DataSource dataSource;
    private DepartmentDAO departmentDAO;

    @Override
    public void init() throws ServletException {
        try {
            super.init();
            departmentDAO = new DepartmentDAOImpl(dataSource);
        } catch (Exception e) {
            throw new ServletException("Failed to initialize DepartmentServlet", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "list": {
                    List<Department> departments = departmentDAO.findAll();
                    request.setAttribute("departments", departments);
                    request.getRequestDispatcher("/department-list.jsp").forward(request, response);
                    break;
                }

                case "new": {
                    request.getRequestDispatcher("/department-form.jsp").forward(request, response);
                    break;
                }

                case "edit": {
                    String idParam = request.getParameter("id");
                    if (idParam != null && !idParam.isEmpty()) {
                        int id = Integer.parseInt(idParam);
                        Department department = departmentDAO.findById(id);
                        if (department != null) {
                            request.setAttribute("department", department);
                            request.getRequestDispatcher("/department-form.jsp").forward(request, response);
                        } else {
                            request.setAttribute("error", "Department not found");
                            response.sendRedirect("departments?action=list");
                        }
                    } else {
                        response.sendRedirect("departments?action=list");
                    }
                    break;
                }

                case "delete": {
                    String idParam = request.getParameter("id");
                    if (idParam != null && !idParam.isEmpty()) {
                        int id = Integer.parseInt(idParam);
                        departmentDAO.delete(id);
                    }
                    response.sendRedirect("departments?action=list");
                    break;
                }

                default: {
                    response.sendRedirect("departments?action=list");
                }
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid department ID");
            request.getRequestDispatcher("/department-list.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("/department-list.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String idParam = request.getParameter("id");
        String name = request.getParameter("name");

        try {
            if (name == null || name.trim().isEmpty()) {
                request.setAttribute("error", "Department name is required");
                request.getRequestDispatcher("/department-form.jsp").forward(request, response);
                return;
            }

            Department department = new Department();
            department.setName(name.trim());

            if (idParam != null && !idParam.isEmpty()) {
                // Update
                int id = Integer.parseInt(idParam);
                department.setId(id);
                departmentDAO.update(department);
            } else {
                // Create
                departmentDAO.create(department);
            }

            response.sendRedirect("departments?action=list");

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid department ID");
            request.getRequestDispatcher("/department-form.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("/department-form.jsp").forward(request, response);
        }
    }
}
