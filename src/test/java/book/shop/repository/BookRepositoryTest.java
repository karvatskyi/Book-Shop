package book.shop.repository;

import book.shop.model.Book;
import book.shop.repository.book.BookRepository;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    @Sql(scripts = {
            "classpath:database/books/add-book-to-books-table.sql",
            "classpath:database/categories/add-category-to-categories-table.sql",
            "classpath:database/books_categories/"
                    + "add-book-and-category-to-books_categories-table.sql"
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {
            "classpath:database/books_categories/cleaning-books_categories-table.sql",
            "classpath:database/books/cleaning-books-table.sql",
            "classpath:database/categories/cleaning-categories-table.sql"
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @DisplayName("""
            Find all books by category id
            """)
    void findAllByCategoryId_ReturnOneBookById() {
        List<Book> allByCategoryId = bookRepository.findAllByCategoryId(1L);
        Assertions.assertEquals(1, allByCategoryId.size());
    }
}
