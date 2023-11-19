package book.shop.dto.order;

import book.shop.model.OrderItem;
import book.shop.model.Status;
import book.shop.model.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class OrderDto {
    @NotEmpty
    private User user;

    @NotEmpty
    private Status status;

    @NotEmpty
    @Positive
    private BigDecimal totalPrice;

    @NotEmpty
    private LocalDateTime orderDate;

    @NotEmpty
    private String shippingAddress;

    @NotEmpty
    private List<OrderItem> orderItems;
}
