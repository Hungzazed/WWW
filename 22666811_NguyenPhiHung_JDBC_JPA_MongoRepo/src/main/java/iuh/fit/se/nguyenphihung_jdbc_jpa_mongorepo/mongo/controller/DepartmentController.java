package iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.mongo.controller;

import iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.mongo.model.Department;
import iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.mongo.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mongo/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.findAllDepartments();
    }

    @GetMapping("/{id}")
    public Optional<Department> getDepartmentById(@PathVariable String id) {
        return departmentService.findDepartmentById(id);
    }

    @GetMapping("/search/name")
    public Department getDepartmentByName(@RequestParam String name) {
        return departmentService.findDepartmentByName(name);
    }

    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.saveDepartment(department);
    }

    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable String id, @RequestBody Department department) {
        department.setId(id);
        return departmentService.updateDepartment(department);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable String id) {
        departmentService.deleteDepartment(id);
    }
}
