package iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.mongo.service;

import iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.mongo.dao.DepartmentRepository;
import iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.mongo.model.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public List<Department> findAllDepartments() {
        return departmentRepository.findAll();
    }
    public Optional<Department> findDepartmentById(String id) {
        return departmentRepository.findById(id);
    }

    public Department findDepartmentByName(String name) {
        return departmentRepository.findByNameIgnoreCase(name);
    }

    // Lưu phòng ban mới
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    // Cập nhật phòng ban
    public Department updateDepartment(Department department) {
        return departmentRepository.save(department);
    }

    // Xóa phòng ban
    public void deleteDepartment(String id) {
        departmentRepository.deleteById(id);
    }
}
