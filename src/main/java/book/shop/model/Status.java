package book.shop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "statuses")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "names", nullable = false)
    private StatusName name;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    public enum StatusName {
        NEW,
        PROCESSING,
        SHIPPED,
        DELIVERED,
        CANCELLED,
        RETURN,
        COMPLETED
    }
}
