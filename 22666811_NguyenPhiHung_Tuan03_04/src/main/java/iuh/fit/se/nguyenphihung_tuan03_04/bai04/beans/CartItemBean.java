package iuh.fit.se.nguyenphihung_tuan03_04.bai04.beans;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartItemBean implements Serializable {
    private Book book;
    private int quantity;

    public double getSubTotal() {
        return book.getPrice() * quantity;
    }
}