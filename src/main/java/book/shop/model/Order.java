package book.shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
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
    @NotEmpty
    @ManyToOne
    private User user;
    @NotEmpty
    @OneToOne
    private Status status;
    @Positive
    private BigDecimal totalPrice;
    @NotEmpty
    private LocalDateTime orderDate;
    @NotEmpty
    private String shippingAddress;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;
}
