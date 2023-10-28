package book.shop.service.shoppingcart;

import book.shop.dto.book.BookDto;
import book.shop.dto.shoppingcart.ShoppingCartDto;

public interface ShoppingCartService {
    ShoppingCartDto addItemToShoppingCart(Long userId, Long bookId, int quantity);

    ShoppingCartDto updateQuantityInShoppingCart(int quantity, Long userId, Long itemId);

    void deleteBookFromShoppingCart(Long userId, BookDto bookDto);
}
