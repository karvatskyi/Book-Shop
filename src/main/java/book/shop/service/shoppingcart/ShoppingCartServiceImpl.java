package book.shop.service.shoppingcart;

import book.shop.dto.book.BookDto;
import book.shop.dto.caritem.CartItemDto;
import book.shop.dto.shoppingcart.ShoppingCartDto;
import book.shop.mapper.BookMapper;
import book.shop.mapper.CartItemMapper;
import book.shop.mapper.ShoppingCartMapper;
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

    private final ShoppingCartMapper shoppingCartMapper;

    private final CartItemMapper cartItemMapper;

    @Override
    public ShoppingCartDto addItemToShoppingCart(Long userId, Long bookId, int quantity) {
        ShoppingCartDto shoppingCartDto = userService.findUserById(userId).getShoppingCart();
        ShoppingCart shoppingCart = shoppingCartMapper.toEntity(shoppingCartDto);
        Set<CartItem> cartItems = shoppingCart.getCartItems();
        CartItemDto cartItemDto = cartItemService.create(shoppingCart, bookId, quantity);
        cartItems.add(cartItemMapper.toEntity(cartItemDto));
        shoppingCart.setCartItems(cartItems);
        shoppingCartRepository.save(shoppingCart);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public ShoppingCartDto updateQuantityInShoppingCart(int quantity, Long userId, Long itemId) {
        ShoppingCartDto shoppingCartDto = userService.findUserById(userId).getShoppingCart();
        ShoppingCart shoppingCart = shoppingCartMapper.toEntity(shoppingCartDto);
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
        return shoppingCartMapper.toDto(shoppingCartRepository.save(shoppingCart));
    }

    @Override
    public void deleteBookFromShoppingCart(Long userId, BookDto bookDto) {
        ShoppingCartDto shoppingCart = userService.findUserById(userId).getShoppingCart();
        Set<CartItem> cartItems = shoppingCart.getCartItems();
        for (CartItem cartItem : cartItems) {
            BookDto bookDtoFromCartItem = bookMapper.toDto(cartItem.getBook());
            if (bookDtoFromCartItem.equals(bookDto)) {
                cartItems.remove(bookDtoFromCartItem);
                break;
            }
        }
        shoppingCartRepository.save(shoppingCartMapper.toEntity(shoppingCart));
    }
}
