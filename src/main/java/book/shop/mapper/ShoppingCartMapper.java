package book.shop.mapper;

import book.shop.config.MapperConfig;
import book.shop.dto.shoppingcart.ShoppingCartDto;
import book.shop.model.ShoppingCart;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface ShoppingCartMapper {
    ShoppingCart toEntity(ShoppingCartDto shoppingCartDto);

    ShoppingCartDto toDto(ShoppingCart shoppingCart);

}
