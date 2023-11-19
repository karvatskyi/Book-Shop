package book.shop.mapper;

import book.shop.config.MapperConfig;
import book.shop.dto.order.OrderDto;
import book.shop.model.Order;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface OrderMapper {
    Order toEntity(OrderDto orderDto);

    OrderDto toDto(Order order);
}
