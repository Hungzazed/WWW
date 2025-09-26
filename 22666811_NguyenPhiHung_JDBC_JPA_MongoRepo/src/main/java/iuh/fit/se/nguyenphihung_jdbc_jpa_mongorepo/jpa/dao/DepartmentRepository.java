package iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.jpa.dao;

import iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.jpa.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Department findByNameIgnoreCase(String name);
}
