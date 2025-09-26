package iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.mongo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "departments")
public class Department {
    @Id
    private String id;
    private String name;
}
