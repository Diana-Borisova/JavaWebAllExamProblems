package softuni.exam.web.services.brand;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.web.repositories.BrandRepository;
import softuni.exam.web.services.init.DataBaseInitServiceService;

@Service
public class BrandServiceImpl implements BrandService, DataBaseInitServiceService {

    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public void dbInit() {

    }

    @Override
    public boolean isDbInit() {
        return this.brandRepository.count() > 0;
    }
}