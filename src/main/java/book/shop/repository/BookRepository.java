package book.shop.repository;

import book.shop.model.Book;
import java.util.List;

public interface BookRepository {

    Book save(Book book);

    List<Book> findAll();

}
