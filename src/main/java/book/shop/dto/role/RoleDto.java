package book.shop.dto.role;

import book.shop.model.Role;
import book.shop.model.User;
import jakarta.validation.constraints.NotEmpty;
import java.util.Set;
import lombok.Data;

@Data
public class RoleDto {
    @NotEmpty
    private Role.RoleName roleName;

    @NotEmpty
    private Set<User> users;
}
