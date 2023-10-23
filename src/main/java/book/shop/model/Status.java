package book.shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private StatusName name;

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
