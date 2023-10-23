package book.shop.service.cartitem;

import book.shop.mapper.BookMapper;
import book.shop.model.CartItem;
import book.shop.model.ShoppingCart;
import book.shop.repository.cartitem.CartItemsRepository;
import book.shop.service.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarItemsServiceImpl implements CartItemService {
    private final BookService bookService;

    private final BookMapper bookMapper;

    private final CartItemsRepository cartItemsRepository;

    @Override
    public CartItem getById(Long id) {
        return cartItemsRepository.getReferenceById(id);
    }

    @Override
    public CartItem save(Long id) {
        return cartItemsRepository.save(cartItemsRepository.getReferenceById(id));
    }

    @Override
    public CartItem create(ShoppingCart shoppingCart, Long bookId, int quantity) {
        return new CartItem(shoppingCart, bookMapper
                .toModelFromDto(bookService.findBookById(bookId)), quantity);
    }
}
