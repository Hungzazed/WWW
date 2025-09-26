package iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.jpa.dao;

import iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.jpa.model.Department;
import iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.jpa.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByNameContainingIgnoreCase(String name);
    List<Employee> findByDepartment(Department department);
    @Query("SELECT e FROM Employee e WHERE e.department.id = :departmentId")
    List<Employee> findByDepartmentId(@Param("departmentId") int departmentId);
    List<Employee> findBySalaryBetween(double minSalary, double maxSalary);
    List<Employee> findBySalaryGreaterThanEqual(double salary);
    List<Employee> findBySalaryLessThanEqual(double salary);
}
