package book.shop.dto.category;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CategoryDto {
    @NotEmpty
    private String name;

    private String description;
}
