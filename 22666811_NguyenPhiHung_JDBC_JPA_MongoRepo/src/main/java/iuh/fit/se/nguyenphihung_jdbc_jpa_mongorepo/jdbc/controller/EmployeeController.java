package iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.jdbc.controller;

import iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.jdbc.model.Employee;
import iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.jdbc.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jdbc/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }
    @GetMapping("/{id}")
    public Employee getById(@PathVariable  int id) {
        return employeeService.getById(id);
    }
    @PostMapping
    public void create(@RequestBody Employee employee) {
        employeeService.save(employee);
    }
    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Employee employee) {
        employee.setId(id);
        employeeService.update(employee);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable  int id) {
        employeeService.delete(id);
    }
}
