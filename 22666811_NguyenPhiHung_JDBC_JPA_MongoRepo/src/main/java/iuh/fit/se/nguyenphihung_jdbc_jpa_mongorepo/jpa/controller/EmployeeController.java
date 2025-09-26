package iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.jpa.controller;

import iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.jpa.model.Employee;
import iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.jpa.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jpa/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.findAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        Optional<Employee> employee = employeeService.findEmployeeById(id);
        return employee.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/name")
    public ResponseEntity<List<Employee>> searchEmployeesByName(@RequestParam String name) {
        List<Employee> employees = employeeService.findEmployeesByName(name);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/search/department/{departmentId}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartmentId(@PathVariable int departmentId) {
        List<Employee> employees = employeeService.findEmployeesByDepartmentId(departmentId);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/search/department")
    public ResponseEntity<List<Employee>> getEmployeesByDepartmentName(@RequestParam String departmentName) {
        List<Employee> employees = employeeService.findEmployeesByDepartmentName(departmentName);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/search/salary/range")
    public ResponseEntity<List<Employee>> getEmployeesBySalaryRange(
            @RequestParam double minSalary,
            @RequestParam double maxSalary) {
        List<Employee> employees = employeeService.findEmployeesBySalaryRange(minSalary, maxSalary);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/search/salary/min")
    public ResponseEntity<List<Employee>> getEmployeesBySalaryMin(@RequestParam double salary) {
        List<Employee> employees = employeeService.findEmployeesBySalaryGreaterThanEqual(salary);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/search/salary/max")
    public ResponseEntity<List<Employee>> getEmployeesBySalaryMax(@RequestParam double salary) {
        List<Employee> employees = employeeService.findEmployeesBySalaryLessThanEqual(salary);
        return ResponseEntity.ok(employees);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.ok(savedEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        employee.setId(id);
        Employee updatedEmployee = employeeService.updateEmployee(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
