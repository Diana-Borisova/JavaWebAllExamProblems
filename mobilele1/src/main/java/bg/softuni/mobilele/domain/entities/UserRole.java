package bg.softuni.mobilele.domain.entities;

import bg.softuni.mobilele.domain.enums.Role;

import jakarta.persistence.*;
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