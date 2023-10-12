package book.shop.dto.user;

import book.shop.model.Role;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

@Data
public class UserResponseDto {

    @NotEmpty
    @UniqueElements
    private String email;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    private String shippingAddress;

    private List<Role.RoleName> roles;
}
