package book.shop.dto.category;

import book.shop.model.Book;
import jakarta.validation.constraints.NotEmpty;
import java.util.Set;
import lombok.Data;

@Data
public class CategoryDto {
    @NotEmpty
    private String name;

    private String description;

    private Set<Book> books;
}
