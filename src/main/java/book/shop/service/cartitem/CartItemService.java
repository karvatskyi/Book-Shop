package book.shop.service.cartitem;

import book.shop.dto.caritem.CartItemDto;
import book.shop.model.ShoppingCart;

public interface CartItemService {
    CartItemDto getById(Long id);

    CartItemDto save(Long id);

    CartItemDto create(ShoppingCart shoppingCart, Long bookId, int quantity);
}
