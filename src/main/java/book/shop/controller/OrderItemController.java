package book.shop.controller;

import book.shop.model.Order;
import book.shop.model.OrderItem;
import book.shop.service.order.OrderService;
import book.shop.service.orderitem.OrderItemService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/orderItem")
public class OrderItemController {
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @GetMapping(value = "/getAllItems")
    public List<OrderItem> getAllItemsFromOrder(Order order) {
        return orderItemService.getAllItemFromOrder(order);
    }

    public OrderItem getItemFromOrder(Order order, Long itemId) {
        return orderItemService.getItemFromOrder(order, itemId);
    }
}
