package book.shop.dto.caritem;

import book.shop.model.Book;
import book.shop.model.ShoppingCart;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CartItemDto {
    @NotEmpty
    private ShoppingCart shoppingCart;

    @NotEmpty
    private Book book;

    @Positive
    @NotEmpty
    private int quantity;
}
