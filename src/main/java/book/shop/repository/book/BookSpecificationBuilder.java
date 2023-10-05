package book.shop.repository.book;

import book.shop.dto.BookSearchParametersDto;
import book.shop.model.Book;
import book.shop.repository.SpecificationBuilder;
import book.shop.repository.SpecificationProviderManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {

    private final SpecificationProviderManager<Book> specificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParametersDto searchParameters) {
        Specification<Book> spec = Specification.where(null);
        if (searchParameters.authors() != null && searchParameters.authors().length > 0) {
            spec = spec.and(specificationProviderManager.getSpecificationProvider("author")
                    .getSpecification(searchParameters.authors()));
        }
        if (searchParameters.prices() != null && searchParameters.prices().length > 0) {
            spec = spec.and(specificationProviderManager.getSpecificationProvider("price")
                    .getSpecification(searchParameters.prices()));
        }
        return spec;
    }
}
