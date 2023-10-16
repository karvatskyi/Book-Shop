package book.shop.service.user;

import book.shop.dto.user.UserRegistrationRequestDto;
import book.shop.dto.user.UserResponseDto;
import book.shop.exception.RegistrationException;
import book.shop.mapper.UserMapper;
import book.shop.model.User;
import book.shop.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("User is registered");
        }
        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        user.setShippingAddress(requestDto.getShippingAddress());
        user.setRoles(requestDto.getRoles());
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }
}
