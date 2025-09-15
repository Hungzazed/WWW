package iuh.fit.se.nguyenphihung_tuan03_04.bai03.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Product {
    private int id;
    private String model;
    private String description;
    private int quantity;
    private double price;
    private String imgUrl;

}
