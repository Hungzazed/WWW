package iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.jpa.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private int id;
    private String name;
    @OneToMany(mappedBy = "department")
    private Set<Employee> employees;
}
