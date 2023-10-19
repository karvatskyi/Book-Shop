package book.shop.service.shoppingcart;

import book.shop.dto.book.BookDto;
import book.shop.mapper.BookMapper;
import book.shop.model.CartItem;
import book.shop.model.ShoppingCart;
import book.shop.repository.shoppingcart.ShoppingCartRepository;
import book.shop.service.cartitem.CartItemService;
import book.shop.service.user.UserService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final UserService userService;

    private final CartItemService cartItemService;

    private final ShoppingCartRepository shoppingCartRepository;

    private final BookMapper bookMapper;

    @Override
    public ShoppingCart addItemToShoppingCart(Long userId, Long bookId, int quantity) {
        ShoppingCart shoppingCart = userService.findUserById(userId).getShoppingCart();
        CartItem cartItem = cartItemService.create(shoppingCart, bookId, quantity);
        cartItemService.save(cartItem.getId());
        Set<CartItem> cartItems = shoppingCart.getCartItems();
        cartItems.add(cartItem);
        shoppingCart.setCartItems(cartItems);
        shoppingCartRepository.save(shoppingCart);
        return shoppingCart;
    }

    @Override
    public ShoppingCart updateQuantityInShoppingCart(int quantity, Long userId, Long itemId) {
        ShoppingCart shoppingCart = userService.findUserById(userId).getShoppingCart();
        Set<CartItem> cartItems = shoppingCart.getCartItems();
        CartItem itemWhatNeedToUpdate = null;
        for (CartItem cartItem : cartItems) {
            if (cartItem.getId().equals(itemId)) {
                itemWhatNeedToUpdate = cartItem;
            }
        }
        if (itemWhatNeedToUpdate != null) {
            cartItems.add(itemWhatNeedToUpdate);
        }
        shoppingCart.setCartItems(cartItems);
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void deleteBookFromShoppingCart(Long userId, BookDto bookDto) {
        ShoppingCart shoppingCart = userService.findUserById(userId).getShoppingCart();
        Set<CartItem> cartItems = shoppingCart.getCartItems();
        for (CartItem cartItem : cartItems) {
            BookDto bookDtoFromCartItem = bookMapper.toDto(cartItem.getBook());
            if (bookDtoFromCartItem.equals(bookDto)) {
                cartItems.remove(bookDtoFromCartItem);
                break;
            }
        }
        shoppingCartRepository.save(shoppingCart);
    }
}
