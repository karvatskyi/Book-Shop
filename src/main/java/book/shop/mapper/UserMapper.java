package book.shop.mapper;

import book.shop.config.MapperConfig;
import book.shop.dto.user.UserRegistrationRequestDto;
import book.shop.dto.user.UserResponseDto;
import book.shop.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {

    UserResponseDto toDto(User user);

    User toModel(UserRegistrationRequestDto userRegistrationRequestDto);

}
