package book.shop.service.orderitem;

import book.shop.dto.orderitem.OrderItemDto;
import book.shop.mapper.OrderItemMapper;
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

    private final OrderItemMapper orderItemMapper;

    @Override
    public List<OrderItemDto> getAllItemFromOrder(Order order) {
        return orderRepository.getReferenceById(order.getId()).getOrderItems().stream()
                .map(orderItemMapper::toDto)
                .toList();
    }

    @Override
    public OrderItemDto getItemFromOrder(Order order, Long itemId) {
        List<OrderItem> orderItems = orderRepository
                .getReferenceById(order.getId()).getOrderItems();
        return orderItemMapper.toDto(orderItems.get(itemId.intValue()));
    }
}
