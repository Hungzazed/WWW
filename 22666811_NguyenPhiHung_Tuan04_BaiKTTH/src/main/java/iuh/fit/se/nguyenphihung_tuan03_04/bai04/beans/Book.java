package iuh.fit.se.nguyenphihung_tuan03_04.bai04.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Book {
    private int id;
    private String title;
    private String author;
    private double price;
    private int quantity;
    private String imgUrl;
}