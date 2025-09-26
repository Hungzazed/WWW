package iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.jdbc.dao;

import iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.jdbc.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeDAO {
    void save(Employee employee);
    void update(Employee employee);
    Employee getById(int id);
    List<Employee> getAll();
    void delete(int id);
}
