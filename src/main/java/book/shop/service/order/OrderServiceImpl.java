package book.shop.service.order;

import book.shop.model.CartItem;
import book.shop.model.Order;
import book.shop.model.OrderItem;
import book.shop.model.ShoppingCart;
import book.shop.model.Status;
import book.shop.repository.order.OrderRepository;
import book.shop.repository.orderitem.OrderItemRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Data
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public void completeOrder(ShoppingCart cart, String shippingAddress) {
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setStatus(new Status());
        order.setTotalPrice(calculatePriceOfAllBooks(cart.getCartItems()));
        order.setOrderDate(LocalDateTime.now());
        order.setShippingAddress(shippingAddress);
        order.setOrderItems(setCartItemToOrderItem(cart.getCartItems(), order));
        orderRepository.save(order);
    }

    @Override
    public void updateStatus(Order order, Status statusName) {
        Order orderFromDB = orderRepository.getReferenceById(order.getId());
        orderFromDB.setStatus(statusName);
    }

    private BigDecimal calculatePriceOfAllBooks(Set<CartItem> cartItems) {
        BigDecimal sum = BigDecimal.ZERO;
        for (CartItem cartItem : cartItems) {
            sum = sum.add(cartItem.getBook().getPrice());
        }
        return sum;
    }

    private List<OrderItem> setCartItemToOrderItem(Set<CartItem> cartItems, Order order) {
        List<OrderItem> result = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setPrice(cartItem.getBook().getPrice());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrder(order);
            result.add(orderItem);
            orderItemRepository.save(orderItem);
        }
        return result;
    }
}
