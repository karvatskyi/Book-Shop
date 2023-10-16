package book.shop.service.user;

import book.shop.dto.user.UserRegistrationRequestDto;
import book.shop.dto.user.UserResponseDto;
import book.shop.exception.RegistrationException;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException;
}
