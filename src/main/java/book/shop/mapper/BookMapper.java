package book.shop.mapper;

import book.shop.config.MapperConfig;
import book.shop.dto.book.BookDto;
import book.shop.dto.book.CreateBookRequestDto;
import book.shop.model.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto createBookRequestDto);
}
