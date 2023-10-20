package book.shop.service.cartitem;

import book.shop.model.CartItem;
import book.shop.model.ShoppingCart;

public interface CartItemService {
    CartItem getById(Long id);

    CartItem save(Long id);

    CartItem create(ShoppingCart shoppingCart, Long bookId, int quantity);
}
