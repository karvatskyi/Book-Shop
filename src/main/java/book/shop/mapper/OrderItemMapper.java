package book.shop.mapper;

import book.shop.config.MapperConfig;
import book.shop.dto.orderitem.OrderItemDto;
import book.shop.model.OrderItem;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface OrderItemMapper {
    OrderItem toEntity(OrderItemMapper orderItemDto);

    OrderItemDto toDto(OrderItem orderItem);
}
