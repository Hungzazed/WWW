package iuh.fit.se.nguyenphihung_tuan03_04.bai01.servlet;

import iuh.fit.se.nguyenphihung_tuan03_04.bai01.model.Student;
import iuh.fit.se.nguyenphihung_tuan03_04.bai01.utils.StudentUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/student_servlet")
public class StudentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private StudentUtil studentUtil;
    @Resource(name = "jdbc/storedb")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();
        studentUtil = new StudentUtil(dataSource);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String dob = request.getParameter("dob");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String pinCode = request.getParameter("pincode");
        String state = request.getParameter("state");
        String country = request.getParameter("country");

        String[] hobbiesArr = request.getParameterValues("hobbies");
        List<String> hobbies = (hobbiesArr != null) ? Arrays.asList(hobbiesArr) : new ArrayList<>();

        String boardX = request.getParameter("boardX");
        String percentageX = request.getParameter("percentageX");
        String yearX = request.getParameter("yearX");

        String boardXII = request.getParameter("boardXII");
        String percentageXII = request.getParameter("percentageXII");
        String yearXII = request.getParameter("yearXII");

        String course = request.getParameter("course");
        Student student = new Student(firstName, lastName, dob, email, mobile,
                gender, address, city, pinCode, state, country,
                hobbies, boardX, percentageX, yearX,
                boardXII, percentageXII, yearXII, course);

        try {
            studentUtil.addStudent(student);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        List<Student> students = null;
        try {
            students = studentUtil.getStudents();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("students", students);
        request.getRequestDispatcher("bai01/student-result.jsp").forward(request, response);
    }
}
