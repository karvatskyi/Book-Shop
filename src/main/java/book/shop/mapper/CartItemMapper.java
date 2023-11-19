package book.shop.mapper;

import book.shop.config.MapperConfig;
import book.shop.dto.caritem.CartItemDto;
import book.shop.model.CartItem;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CartItemMapper {
    CartItem toEntity(CartItemDto cartItemDto);

    CartItemDto toDto(CartItem cartItem);
}
