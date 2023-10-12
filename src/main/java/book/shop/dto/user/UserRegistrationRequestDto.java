package book.shop.dto.user;

import book.shop.anotation.FieldMatch;
import book.shop.model.Role;
import jakarta.validation.constraints.NotEmpty;
import java.util.Set;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "repeatPassword",
                message = "Field password and repeatPassword must be equal")
})
@Data
public class UserRegistrationRequestDto {

    @NotEmpty
    @UniqueElements
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String repeatPassword;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    private String shippingAddress;

    private Set<Role.RoleName> roles;
}
