package book.shop.controller;

import book.shop.dto.user.UserLoginRequestDto;
import book.shop.dto.user.UserLoginResponseDto;
import book.shop.dto.user.UserRegistrationRequestDto;
import book.shop.dto.user.UserResponseDto;
import book.shop.exception.RegistrationException;
import book.shop.security.AuthenticationService;
import book.shop.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Security management", description = "Endpoints for authentication")
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @Operation(summary = "Register method", description = "Registration of user")
    public UserResponseDto register(@RequestBody @Valid UserRegistrationRequestDto request)
            throws RegistrationException {
        return userService.register(request);
    }

    @PostMapping("/login")
    @Operation(summary = "Login method", description = "Login of user")
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto request) {
        return authenticationService.authenticate(request);
    }
}
