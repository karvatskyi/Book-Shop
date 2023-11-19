package book.shop.controller;

import book.shop.dto.order.OrderDto;
import book.shop.model.Order;
import book.shop.model.ShoppingCart;
import book.shop.model.Status;
import book.shop.service.order.OrderService;
import book.shop.service.user.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping("/completeOrder")
    public void completeOrder(ShoppingCart cart, String shippingAddress) {
        orderService.completeOrder(cart, shippingAddress);
    }

    @GetMapping("/history")
    public List<OrderDto> getOrderHistory(Long userId) {
        return orderService.getHistory(userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/updateStatus")
    public void updateStatus(Order order, Status statusName) {
        orderService.updateStatus(order, statusName);
    }
}
