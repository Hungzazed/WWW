package iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.mongo.dao;

import iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.mongo.model.Department;
import iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.mongo.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
    List<Employee> findByNameContainingIgnoreCase(String name);
    List<Employee> findByDepartment(Department department);
    @Query("{'department.$id': ?0}")
    List<Employee> findByDepartmentId(String departmentId);
    List<Employee> findBySalaryBetween(double minSalary, double maxSalary);
    List<Employee> findBySalaryGreaterThanEqual(double salary);
    List<Employee> findBySalaryLessThanEqual(double salary);
}
