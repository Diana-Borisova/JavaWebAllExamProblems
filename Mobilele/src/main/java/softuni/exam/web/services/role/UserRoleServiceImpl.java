package softuni.exam.web.services.role;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.web.domain.dtos.view.UserRoleViewDto;
import softuni.exam.web.domain.entities.UserRole;
import softuni.exam.web.domain.enums.Role;
import softuni.exam.web.repositories.UserRoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.dbInit();
    }


    @Override
    public void dbInit() {
        if (!isDbInit()) {
            List<UserRole> roles = new ArrayList<>();

            roles.add(new UserRole().setRole(Role.User));
            roles.add(new UserRole().setRole(Role.Admin));

            this.roleRepository.saveAllAndFlush(roles);
        }
    }

    @Override
    public boolean isDbInit() {
        return this.roleRepository.count() > 0;
    }

    public List<UserRoleViewDto> getAll() {
        return this.roleRepository.findAll()
                .stream()
                .map(r -> this.modelMapper.map(r, UserRoleViewDto.class))
                .collect(Collectors.toList());
    }
}