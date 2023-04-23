package softuni.exam.web.domain.dtos.view;

import softuni.exam.web.domain.enums.Role;

public class UserRoleViewDto {
    private String role;

    public String getRole() {
        return role;
    }

    public UserRoleViewDto setRole(String role) {
        this.role = role;
        return this;
    }
}