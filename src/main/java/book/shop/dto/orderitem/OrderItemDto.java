package book.shop.dto.orderitem;

import book.shop.model.Book;
import book.shop.model.Order;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class OrderItemDto {
    @NotEmpty
    private Order order;

    @NotEmpty
    private Book book;

    @NotEmpty
    @Positive
    private int quantity;

    @NotEmpty
    @Positive
    private BigDecimal price;
}
