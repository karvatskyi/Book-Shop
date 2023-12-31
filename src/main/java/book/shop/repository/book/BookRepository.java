package book.shop.repository.book;

import book.shop.dto.book.BookDto;
import book.shop.model.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    @Query("SELECT b from Book b inner join b.categories c where c.id = :id")
    List<BookDto> findAllByCategoryId(@Param("id") Long id);
}
