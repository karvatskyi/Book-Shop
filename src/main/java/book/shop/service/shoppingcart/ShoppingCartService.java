package book.shop.service.shoppingcart;

import book.shop.dto.book.BookDto;
import book.shop.model.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCart addItemToShoppingCart(Long userId, Long bookId, int quantity);

    ShoppingCart updateQuantityInShoppingCart(int quantity, Long userId, Long itemId);

    void deleteBookFromShoppingCart(Long userId, BookDto bookDto);
}
