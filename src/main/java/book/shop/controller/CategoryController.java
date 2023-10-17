package book.shop.controller;

import book.shop.dto.book.BookDto;
import book.shop.dto.category.CategoryDto;
import book.shop.repository.book.BookRepository;
import book.shop.service.category.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Categories management", description = "Endpoints for managing category")
@RequiredArgsConstructor
@RequestMapping(value = "/categories")
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    private final BookRepository bookRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Operation(summary = "Create a category", description = "Create a category")
    public CategoryDto createCategory(CategoryDto categoryDto) {
        return categoryService.save(categoryDto);
    }

    @GetMapping()
    @Operation(summary = "Get all categories",
            description = "Get a list of all available categories")
    public List<CategoryDto> getAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category by id", description = "Get available category")
    public CategoryDto getCategoryById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/updateCategoryById")
    @Operation(summary = "Update a category by id", description = "Update a category by id")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateCategory(Long id, CategoryDto categoryDto) {
        categoryService.update(id, categoryDto);
    }

    @DeleteMapping
    @Operation(summary = "Delete a category", description = "Delete a category")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCategory(Long id) {
        categoryService.deleteById(id);
    }

    @GetMapping(value = "/{id}/books")
    @Operation(summary = "Get book", description = "Get available book by category id")
    public List<BookDto> getBooksByCategory(Long id) {
        return bookRepository.findAllByCategoryId(id);
    }
}
