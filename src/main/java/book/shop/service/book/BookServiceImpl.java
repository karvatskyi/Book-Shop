package book.shop.service.book;

import book.shop.dto.book.BookDto;
import book.shop.dto.book.BookSearchParametersDto;
import book.shop.dto.book.CreateBookRequestDto;
import book.shop.exception.EntityNotFoundException;
import book.shop.mapper.BookMapper;
import book.shop.model.Book;
import book.shop.repository.book.BookRepository;
import book.shop.repository.book.BookSpecificationBuilder;
import java.awt.print.Pageable;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    private final BookSpecificationBuilder bookSpecificationBuilder;

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toEntity(requestDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto findBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't get book by id: " + id)
        );
        return bookMapper.toDto(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookDto updateBookById(Long id, CreateBookRequestDto requestDto) {
        Book bookFromDb = bookRepository.getReferenceById(id);
        bookFromDb.setAuthor(requestDto.getAuthor());
        bookFromDb.setIsbn(requestDto.getIsbn());
        bookFromDb.setDescription(requestDto.getDescription());
        bookFromDb.setPrice(requestDto.getPrice());
        bookFromDb.setCoverImage(requestDto.getCoverImage());
        bookFromDb.setTitle(requestDto.getTitle());
        return bookMapper.toDto(bookFromDb);
    }

    @Override
    public List<BookDto> search(BookSearchParametersDto params) {
        Specification<Book> bookSpecification = bookSpecificationBuilder.build(params);
        return bookRepository.findAll(bookSpecification).stream()
                .map(bookMapper::toDto)
                .toList();
    }
}
