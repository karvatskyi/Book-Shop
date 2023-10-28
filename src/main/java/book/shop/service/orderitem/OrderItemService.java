package book.shop.service.orderitem;

import book.shop.dto.orderitem.OrderItemDto;
import book.shop.model.Order;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface OrderItemService {
    List<OrderItemDto> getAllItemFromOrder(Order order);

    OrderItemDto getItemFromOrder(Order order, Long itemId);
}
