package book.shop.controller;

import book.shop.dto.user.UserLoginRequestDto;
import book.shop.dto.user.UserLoginResponseDto;
import book.shop.dto.user.UserRegistrationRequestDto;
import book.shop.dto.user.UserResponseDto;
import book.shop.exception.RegistrationException;
import book.shop.security.AuthenticationService;
import book.shop.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody @Valid UserRegistrationRequestDto request)
            throws RegistrationException {
        return userService.register(request);
    }

    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto request) {
        return authenticationService.authenticate(request);
    }
}
