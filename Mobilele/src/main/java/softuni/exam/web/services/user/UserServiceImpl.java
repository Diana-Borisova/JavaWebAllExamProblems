package softuni.exam.web.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.web.repositories.UserRepository;
import softuni.exam.web.services.init.DataBaseInitServiceService;

@Service
public class UserServiceImpl implements UserService, DataBaseInitServiceService {
    final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void dbInit() {

    }

    @Override
    public boolean isDbInit() {
        return this.userRepository.count() > 0;
    }
}
