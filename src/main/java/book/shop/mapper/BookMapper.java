package book.shop.mapper;

import book.shop.config.MapperConfig;
import book.shop.dto.book.BookDto;
import book.shop.dto.book.BookDtoWithoutCategoryIds;
import book.shop.dto.book.CreateBookRequestDto;
import book.shop.model.Book;
import book.shop.model.Category;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toEntity(CreateBookRequestDto createBookRequestDto);

    Book toModelFromDto(BookDto bookDto);

    BookDtoWithoutCategoryIds toDtoWithoutCategories(Book book);

    @AfterMapping
    default List<Long> setCategoryIds(Set<Category> categories) {
        return categories.stream()
                .map(Category::getId)
                .collect(Collectors.toList());
    }

    @AfterMapping
    default Set<Category> setIds(List<Long> ids) {
        Set<Category> categories = new HashSet<>();
        for (Long id : ids) {
            Category category = new Category();
            category.setId(id);
            categories.add(category);
        }
        return categories;
    }

    default List<Long> mapCategorySetToLongList(Set<Category> categories) {
        return setCategoryIds(categories);
    }

    default Set<Category> mapLongListToCategorySet(List<Long> ids) {
        return setIds(ids);
    }
}

