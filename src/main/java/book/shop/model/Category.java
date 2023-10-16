package book.shop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@SQLDelete(sql = "update books set id_deleted = true where id=?")
@Where(clause = "is_deleted=false")
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "names", nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private boolean isDeleted;

    @ManyToMany(mappedBy = "categories")
    private Set<Book> books;
}
