package book.shop.dto.shoppingcart;

import book.shop.model.CartItem;
import book.shop.model.User;
import jakarta.validation.constraints.NotEmpty;
import java.util.Set;
import lombok.Data;

@Data
public class ShoppingCartDto {
    @NotEmpty
    private User user;

    @NotEmpty
    private Set<CartItem> cartItems;
}
