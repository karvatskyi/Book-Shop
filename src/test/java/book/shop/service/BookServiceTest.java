package book.shop.service;

import book.shop.dto.book.BookDto;
import book.shop.dto.book.CreateBookRequestDto;
import book.shop.mapper.BookMapper;
import book.shop.model.Book;
import book.shop.repository.book.BookRepository;
import book.shop.service.book.BookService;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("""
            Test save method
            """)
    public void save_negativePrice_throwException() {
        CreateBookRequestDto requestDto = initializeBookRequestDto();
        Book book = initializeBook();
        book.setPrice(BigDecimal.valueOf(-1.2));
        BookDto expectedBookDto = initializeBookDto();
        when(bookMapper.toEntity(requestDto)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);
        when(bookMapper.toDto(book)).thenReturn(expectedBookDto);
        BookDto actual = bookService.save(requestDto);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(actual, expectedBookDto);
    }

    private BookDto initializeBookDto() {
        BookDto bookdto = new BookDto();
        bookdto.setCategories(List.of());
        bookdto.setCoverImage("cover image");
        bookdto.setTitle("title");
        bookdto.setIsbn("isbn");
        bookdto.setPrice(BigDecimal.valueOf(33.99));
        bookdto.setAuthor("author");
        bookdto.setDescription("desc");
        return bookdto;
    }

    private Book initializeBook() {
        Book book = new Book();
        book.setCoverImage("cover image");
        book.setTitle("title");
        book.setIsbn("isbn");
        book.setPrice(BigDecimal.valueOf(33.99));
        book.setAuthor("author");
        book.setDescription("desc");
        book.setDeleted(false);
        return book;
    }

    private CreateBookRequestDto initializeBookRequestDto() {
        CreateBookRequestDto requestDto = new CreateBookRequestDto();
        requestDto.setCoverImage("cover image");
        requestDto.setTitle("title");
        requestDto.setIsbn("isbn");
        requestDto.setPrice(BigDecimal.valueOf(33.99));
        requestDto.setAuthor("author");
        requestDto.setDescription("desc");
        return requestDto;
    }
}
