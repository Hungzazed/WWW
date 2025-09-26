package iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.mongo.dao;

import iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.mongo.model.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends MongoRepository<Department, String> {
    Department findByNameIgnoreCase(String name);
}
