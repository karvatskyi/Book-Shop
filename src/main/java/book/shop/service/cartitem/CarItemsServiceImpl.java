package book.shop.service.cartitem;

import book.shop.dto.caritem.CartItemDto;
import book.shop.mapper.BookMapper;
import book.shop.mapper.CartItemMapper;
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

    private final CartItemMapper cartItemMapper;

    @Override
    public CartItemDto getById(Long id) {
        return cartItemMapper.toDto(cartItemsRepository.getReferenceById(id));
    }

    @Override
    public CartItemDto save(Long id) {
        return cartItemMapper.toDto(cartItemsRepository
                .save(cartItemsRepository.getReferenceById(id)));
    }

    @Override
    public CartItemDto create(ShoppingCart shoppingCart, Long bookId, int quantity) {
        return cartItemMapper.toDto(new CartItem(
                shoppingCart,
                bookMapper.toModelFromDto(bookService.findBookById(bookId)),
                quantity)
        );
    }
}
