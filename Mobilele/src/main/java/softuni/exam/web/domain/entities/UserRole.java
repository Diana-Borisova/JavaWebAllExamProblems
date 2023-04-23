package softuni.exam.web.domain.entities;


import jakarta.persistence.*;
import softuni.exam.web.domain.enums.Role;

@Entity
@Table(name = "roles")
public class UserRole extends BaseEntity {

    @Column(name = "name")
    @Enumerated(EnumType.ORDINAL)
    private Role role;

    public Role getRole() {
        return role;
    }

    public UserRole setRole(Role role) {
        this.role = role;
        return this;
    }
}