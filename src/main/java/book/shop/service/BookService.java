package book.shop.service;

import book.shop.dto.BookDto;
import book.shop.dto.BookSearchParametersDto;
import book.shop.dto.CreateBookRequestDto;
import java.awt.print.Pageable;
import java.util.List;

public interface BookService {

    BookDto save(CreateBookRequestDto requestDto);

    List<BookDto> findAll(Pageable pageable);

    BookDto findBookById(Long id);

    void deleteById(Long id);

    BookDto updateBookById(Long id, CreateBookRequestDto requestDto);

    List<BookDto> search(BookSearchParametersDto params);
}
