package book.shop.service.order;

import book.shop.model.Order;
import book.shop.model.ShoppingCart;
import book.shop.model.Status;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    void completeOrder(ShoppingCart cart, String shippingAddress);

    void updateStatus(Order order, Status statusName);

}
