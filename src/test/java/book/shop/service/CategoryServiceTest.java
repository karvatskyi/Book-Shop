package book.shop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import book.shop.dto.category.CategoryDto;
import book.shop.mapper.CategoryMapper;
import book.shop.model.Category;
import book.shop.repository.category.CategoryRepository;
import book.shop.service.category.CategoryServiceImpl;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    @DisplayName("""
            Test findAll method, valid result
            """)
    public void findAll_validParameters_ok() {
        List<Category> categories = List.of(initializeCategory());
        List<CategoryDto> expected = List.of(initializeCategoryDto());
        when(categoryRepository.findAll()).thenReturn(categories);
        when(categoryMapper.toDto(categories.get(0))).thenReturn(expected.get(0));

        List<CategoryDto> actual = categoryService.findAll();

        Assertions.assertEquals(actual.size(), expected.size());
    }

    @Test
    @DisplayName("""
            Test getById method, valid result
            """)
    public void getById_validParameters_ok() {
        Category category = initializeCategory();
        CategoryDto expected = initializeCategoryDto();

        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));
        when(categoryMapper.toDto(category)).thenReturn(expected);
        CategoryDto actual = categoryService.getById(anyLong());
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("""
            Test save method, valid result
            """)
    public void save_validParameters_ok() {
        CategoryDto expected = initializeCategoryDto();
        Category category = initializeCategory();
        when(categoryMapper.toEntity(expected)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(category);
        when(categoryMapper.toDto(category)).thenReturn(expected);

        CategoryDto actual = categoryService.save(expected);
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("""
            Test update method, valid result
            """)
    public void update_validParameters_ok() {
        Long categoryId = 1L;
        CategoryDto categoryDto = initializeCategoryDto();
        Category category = initializeCategory();

        when(categoryMapper.toEntity(categoryDto)).thenReturn(category);

        categoryService.updateById(categoryId, categoryDto);

        verify(categoryMapper, times(1)).toEntity(categoryDto);
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    @DisplayName("""
            Test deleteById method, valid result
            """)
    public void deleteById_validParameters_ok() {
        Long categoryId = 1L;
        categoryService.deleteById(categoryId);
        verify(categoryRepository, times(1)).deleteById(categoryId);
    }

    private Category initializeCategory() {
        Category category = new Category();
        category.setName("categoryName");
        category.setDeleted(false);
        return category;
    }

    private CategoryDto initializeCategoryDto() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("categoryDtoName");
        return categoryDto;
    }
}
