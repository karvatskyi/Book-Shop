package book.shop.service.order;

import book.shop.dto.order.OrderDto;
import book.shop.model.Order;
import book.shop.model.ShoppingCart;
import book.shop.model.Status;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    void completeOrder(ShoppingCart cart, String shippingAddress);

    void updateStatus(Order order, Status statusName);

    List<OrderDto> getHistory(Long userid);

}
