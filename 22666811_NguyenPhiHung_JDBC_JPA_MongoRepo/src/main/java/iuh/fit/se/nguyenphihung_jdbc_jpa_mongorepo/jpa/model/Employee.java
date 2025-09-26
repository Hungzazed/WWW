package iuh.fit.se.nguyenphihung_jdbc_jpa_mongorepo.jpa.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.time.LocalDate;

@Data
@Entity

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private LocalDate dob;
    private double salary;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
