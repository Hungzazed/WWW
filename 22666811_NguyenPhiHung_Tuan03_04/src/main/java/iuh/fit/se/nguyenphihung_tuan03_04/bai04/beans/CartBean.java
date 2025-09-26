package iuh.fit.se.nguyenphihung_tuan03_04.bai04.beans;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class CartBean implements Serializable {
    private List<CartItemBean> items = new ArrayList<>();
    
    public void addBook(Book book) {
        for (CartItemBean item : items) {
            if (item.getBook().getId() == book.getId()) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        CartItemBean newItem = new CartItemBean();
        newItem.setBook(book);
        newItem.setQuantity(1);
        items.add(newItem);
    }
    
    public void updateQuantity(int bookId, int quantity) {
        for (CartItemBean item : items) {
            if (item.getBook().getId() == bookId) {
                item.setQuantity(quantity);
                return;
            }
        }
    }
    
    public void deleteBook(int bookId) {
        items.removeIf(item -> item.getBook().getId() == bookId);
    }
    
    public void clear() {
        items.clear();
    }
    
    public double getTotal() {
        return items.stream().mapToDouble(CartItemBean::getSubTotal).sum();
    }
}