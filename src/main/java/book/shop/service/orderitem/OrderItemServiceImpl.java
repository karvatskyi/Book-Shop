package book.shop.service.orderitem;

import book.shop.model.Order;
import book.shop.model.OrderItem;
import book.shop.repository.order.OrderRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderRepository orderRepository;

    @Override
    public List<OrderItem> getAllItemFromOrder(Order order) {
        return orderRepository.getReferenceById(order.getId()).getOrderItems();
    }

    @Override
    public OrderItem getItemFromOrder(Order order, Long itemId) {
        return orderRepository.getReferenceById(order.getId())
                .getOrderItems().get(Math.toIntExact(itemId));
    }
}
