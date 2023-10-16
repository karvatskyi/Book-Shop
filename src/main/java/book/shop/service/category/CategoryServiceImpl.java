package book.shop.service.category;

import book.shop.dto.book.BookDto;
import book.shop.dto.category.CategoryDto;
import book.shop.exception.EntityNotFoundException;
import book.shop.mapper.CategoryMapper;
import book.shop.model.Category;
import book.shop.repository.category.CategoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryDto getById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't get category by id: " + id));
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        return categoryMapper.toDto(categoryRepository.save(categoryMapper.toEntity(categoryDto)));
    }

    @Override
    public CategoryDto update(Long id, CategoryDto categoryDto) {
        return categoryRepository.updateById(id, categoryDto);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<BookDto> getBookByCategoryId(Long id) {
        return categoryRepository.getBookByCategoryId(id);
    }
}
