package book.shop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order")
    private User user;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Status status;

    @Column(name = "total_prices", nullable = false)
    private BigDecimal totalPrice;

    @Column(name = "order_dates", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "shipping_addresses")
    private String shippingAddress;

    @OneToMany(mappedBy = "order")
    @Column(name = "order_items")
    private List<OrderItem> orderItems;
}
