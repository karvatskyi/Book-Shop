package book.shop.repository.category;

import book.shop.dto.book.BookDto;
import book.shop.dto.category.CategoryDto;
import book.shop.model.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    CategoryDto updateById(Long id, CategoryDto categoryDto);

    List<BookDto> getBookByCategoryId(Long id);
}

