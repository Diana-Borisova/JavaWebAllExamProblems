package softuni.exam.web.services.role;

import softuni.exam.web.domain.dtos.view.UserRoleViewDto;
import softuni.exam.web.services.init.DataBaseInitServiceService;

import java.util.List;

public interface UserRoleService extends DataBaseInitServiceService {
    List<UserRoleViewDto> getAll();


}