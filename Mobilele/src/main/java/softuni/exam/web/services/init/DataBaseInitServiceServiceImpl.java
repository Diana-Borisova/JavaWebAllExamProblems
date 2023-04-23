package softuni.exam.web.services.init;



import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.web.services.role.UserRoleService;



@Service
public class DataBaseInitServiceServiceImpl implements DataBaseInitServiceService {
    private final UserRoleService roleService;

    @Autowired
    public DataBaseInitServiceServiceImpl(UserRoleService roleService) {
        this.roleService = roleService;
    }

    @PostConstruct
    public void postConstruct() {
        dbInit();
    }

    @Override
    public void dbInit() {
        if (isDbInit()) {
            this.roleService.dbInit();
        }
    }

    @Override
    public boolean isDbInit() {
        return this.roleService.isDbInit();
    }
}
