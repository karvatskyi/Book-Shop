package book.shop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.Set;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Entity
@SQLDelete(sql = "update users set is_deleted = true where id=?")
@Where(clause = "is_deleted=false")
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "emails", nullable = false, unique = true)
    private String email;

    @Column(name = "passwords", nullable = false, unique = true)
    private String password;

    @Column(name = "first_names", nullable = false)
    private String firstName;

    @Column(name = "last_names", nullable = false)
    private String lastName;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "role")
    private Set<Role.RoleName> roles;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !isDeleted;
    }
}
