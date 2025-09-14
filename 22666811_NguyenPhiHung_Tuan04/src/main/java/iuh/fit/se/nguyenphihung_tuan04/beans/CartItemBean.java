package iuh.fit.se.nguyenphihung_tuan04.beans;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartItemBean implements Serializable {
    private Product product;
    private int quantity;

    public double getSubTotal() {
        return product.getPrice() * quantity;
    }
}
