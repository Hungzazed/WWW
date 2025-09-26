package iuh.fit.se.nguyenphihung_tuan05.bai05.dao;

import iuh.fit.se.nguyenphihung_tuan05.bai05.model.Department;

import java.util.List;

public interface DepartmentDAO {
    void create(Department department);
    void update(Department department);
    void delete(int id);
    Department findById(int id);
    List<Department> findAll();
}
