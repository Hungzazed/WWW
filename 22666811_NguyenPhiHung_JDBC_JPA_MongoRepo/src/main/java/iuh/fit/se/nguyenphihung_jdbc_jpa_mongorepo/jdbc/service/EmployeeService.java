package iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.jdbc.service;

import iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.jdbc.dao.EmployeeDAO;
import iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.jdbc.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeDAO employeeDAO;

    public List<Employee> getAll() {
        return employeeDAO.getAll();
    }

    public Employee getById(int id) {
        return employeeDAO.getById(id);
    }

    public void save(Employee employee) {
        employeeDAO.save(employee);
    }

    public void update(Employee employee) {
        employeeDAO.update(employee);
    }
    public void delete(int id) {
        employeeDAO.delete(id);
    }
}
