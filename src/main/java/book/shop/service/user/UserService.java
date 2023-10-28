package book.shop.service.user;

import book.shop.dto.order.OrderDto;
import book.shop.dto.user.UserRegistrationRequestDto;
import book.shop.dto.user.UserResponseDto;
import book.shop.exception.RegistrationException;
import book.shop.model.User;
import java.util.List;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException;

    UserResponseDto findUserById(Long id);

    List<OrderDto> getHistory(User user);
}
