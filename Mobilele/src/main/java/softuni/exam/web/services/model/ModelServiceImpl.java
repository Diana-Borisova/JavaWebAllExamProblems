package softuni.exam.web.services.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.web.repositories.ModelRepository;
import softuni.exam.web.services.init.DataBaseInitServiceService;

@Service
public class ModelServiceImpl implements ModelService, DataBaseInitServiceService {
    private final ModelRepository modelRepository;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public void dbInit() {

    }

    @Override
    public boolean isDbInit() {
        return this.modelRepository.count() > 0;
    }
}