package bg.softuni.mobilele.services.role;

import bg.softuni.mobilele.domain.dtos.view.UserRoleViewDto;
import bg.softuni.mobilele.services.init.DataBaseInitServiceService;

import java.util.List;

public interface UserRoleService extends DataBaseInitServiceService {
    List<UserRoleViewDto> getAll();
}