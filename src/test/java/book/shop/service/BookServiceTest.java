package book.shop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import book.shop.dto.book.BookDto;
import book.shop.dto.book.BookSearchParametersDto;
import book.shop.dto.book.CreateBookRequestDto;
import book.shop.mapper.BookMapper;
import book.shop.model.Book;
import book.shop.repository.book.BookRepository;
import book.shop.repository.book.BookSpecificationBuilder;
import book.shop.service.book.BookServiceImpl;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @Mock
    private BookSpecificationBuilder bookSpecificationBuilder;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    @DisplayName("""
            Test save method, valid result
            """)
    public void save_validParameters_ok() {
        CreateBookRequestDto requestDto = initializeBookRequestDto();
        Book book = initializeBook();
        book.setPrice(BigDecimal.valueOf(-1.2));
        BookDto expected = initializeBookDto();

        when(bookMapper.toEntity(requestDto)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);
        when(bookMapper.toDto(book)).thenReturn(expected);

        BookDto actual = bookService.save(requestDto);
        Assertions.assertNotNull(actual);
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("""
            Test save method when input parameters is empty, should throw exception
            """)
    public void save_emptyInputParameters_shouldThrowException() {
        CreateBookRequestDto requestDto = null;

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class,
                () -> bookService.save(requestDto));

        String expected = "Input parameters can't be null";
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("""
            Test method findBookById ,valid result
            """)
    public void findBookById_validParameters_ok() {
        Book book = initializeBook();
        BookDto expectedBookDto = initializeBookDto();

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookMapper.toDto(book)).thenReturn(expectedBookDto);

        BookDto actual = bookService.findBookById(1L);
        assertEquals(actual, expectedBookDto);
    }

    @Test
    @DisplayName("""
            Test deleteById method, valid result
            """)
    public void deleteById_validParameters_ok() {
        Long bookId = 1L;
        bookService.deleteById(bookId);
        verify(bookRepository).deleteById(bookId);
    }

    @Test
    @DisplayName("""
            Test updateBookById method, valid result
            """)
    public void updateBookById_validParameters_ok() {
        Book book = initializeBook();
        CreateBookRequestDto requestDto = initializeBookRequestDto();

        when(bookRepository.getReferenceById(1L)).thenReturn(book);

        BookDto actual = bookService.updateBookById(1L, requestDto);
        BookDto expected = bookMapper.toDto(book);
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("""
            Test search method, valid result
            """)
    public void search_validParameters_ok() {
        BookSearchParametersDto params =
                new BookSearchParametersDto(new String[]{}, new String[]{});
        Specification<Book> bookSpecification = Specification.where(null);
        List<Book> books = List.of(initializeBook());
        List<BookDto> expected = List.of(initializeBookDto());

        when(bookSpecificationBuilder.build(params)).thenReturn(bookSpecification);
        when(bookRepository.findAll(bookSpecification)).thenReturn(books);
        when(bookMapper.toDto(books.get(0))).thenReturn(expected.get(0));

        List<BookDto> actual = bookService.search(params);

        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("""
            Test findAll method, valid result
            """)
    public void findAll_validParameters_ok() {
        List<Book> books = List.of(initializeBook());
        List<BookDto> bookDtos = List.of(initializeBookDto());

        Pageable pageable = PageRequest.of(0, 10);
        PageImpl<Book> page = new PageImpl<>(books, pageable, books.size());
        when(bookRepository.findAll()).thenReturn(books);
        when(bookMapper.toDto(books.get(0))).thenReturn(bookDtos.get(0));

        List<BookDto> result = bookService.findAll(pageable);

        assertEquals(bookDtos.size(), result.size());
        assertEquals(bookDtos.get(0), result.get(0));
    }

    private BookDto initializeBookDto() {
        BookDto bookDto = new BookDto();
        bookDto.setCategories(List.of());
        bookDto.setCoverImage("cover image");
        bookDto.setTitle("title");
        bookDto.setIsbn("isbn");
        bookDto.setPrice(BigDecimal.valueOf(34.99));
        bookDto.setAuthor("author");
        bookDto.setDescription("desc");
        return bookDto;
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
