package book.shop.mapper;

import book.shop.config.MapperConfig;
import book.shop.dto.category.CategoryDto;
import book.shop.model.Category;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    Category toEntity(CategoryDto categoryDtO);
}
