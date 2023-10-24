package book.shop.dto.book;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class BookDto {
    @NotEmpty
    private String title;

    @NotEmpty
    private String author;

    @NotEmpty
    private String isbn;

    @NotEmpty
    @Positive
    private BigDecimal price;

    @NotEmpty
    private String description;

    @NotEmpty
    private String coverImage;

    @NotEmpty
    private List<Long> categories = new ArrayList<>();
}
