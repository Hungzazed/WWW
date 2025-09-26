package iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.mongo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;

@Data
@Document(collection = "employees")
public class Employee {
    @Id
    private String id;
    private String name;
    private LocalDate dob;
    private double salary;

    @DBRef
    private Department department;
}
