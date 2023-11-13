package book.shop.repository;

import book.shop.dto.book.BookDto;
import book.shop.model.Book;
import book.shop.model.Category;
import book.shop.repository.book.BookRepository;
import book.shop.repository.category.CategoryRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("""
            Find all books when inside DB just 1 element
            """)
    public void findAllByCategoryId_OneElementInDb_ReturnTrue() {
        Book book = new Book();
        book.setCategories(Set.of(new Category()));
        book.setPrice(BigDecimal.TEN);
        book.setTitle("title");
        book.setIsbn("12412");
        book.setAuthor("author");
        book.setDeleted(false);
        bookRepository.save(book);
        int expected = 1;

        List<BookDto> actual = bookRepository.findAllByCategoryId(1L);

        Assertions.assertEquals(expected, actual.size());
    }
}
