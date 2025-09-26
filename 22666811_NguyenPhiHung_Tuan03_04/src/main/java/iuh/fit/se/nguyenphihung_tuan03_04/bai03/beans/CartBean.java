package iuh.fit.se.nguyenphihung_tuan03_04.bai03.beans;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class CartBean implements Serializable {
    private List<CartItemBean> items = new ArrayList<>();
    public void addProduct(Product product) {
        for (CartItemBean item : items) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        CartItemBean newItem = new CartItemBean();
        newItem.setProduct(product);
        newItem.setQuantity(1);
        items.add(newItem);
    }
    public void updateQuantity(int productId, int quantity) {
        for (CartItemBean item : items) {
            if (item.getProduct().getId() == productId) {
                item.setQuantity(quantity);
                return;
            }
        }
    }
    public void deleteProduct(int productId) {
        items.removeIf(item -> item.getProduct().getId() == productId);
    }
    public void clear() {
        items.clear();
    }
    public double getTotal() {
        return items.stream().mapToDouble(CartItemBean::getSubTotal).sum();
    }
}
