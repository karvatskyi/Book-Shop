package book.shop.service.orderitem;

import book.shop.model.Order;
import book.shop.model.OrderItem;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface OrderItemService {
    List<OrderItem> getAllItemFromOrder(Order order);

    OrderItem getItemFromOrder(Order order, Long itemId);

}
