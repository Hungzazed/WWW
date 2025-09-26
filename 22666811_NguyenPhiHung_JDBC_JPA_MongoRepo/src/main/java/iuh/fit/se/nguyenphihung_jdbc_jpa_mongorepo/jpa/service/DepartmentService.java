package iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.jpa.service;

import iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.jpa.dao.DepartmentRepository;
import iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.jpa.model.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(int id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(int id, Department department) {
        department.setId(id);
        return departmentRepository.save(department);
    }

    public void deleteDepartment(int id) {
        departmentRepository.deleteById(id);
    }

    public Department findByNameIgnoreCase(String name) {
        return departmentRepository.findByNameIgnoreCase(name);
    }
}
