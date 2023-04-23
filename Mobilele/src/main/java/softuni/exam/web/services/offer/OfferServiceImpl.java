package softuni.exam.web.services.offer;

import org.springframework.stereotype.Service;
import softuni.exam.web.services.init.DataBaseInitServiceService;

@Service
public class OfferServiceImpl implements OfferService, DataBaseInitServiceService {
    @Override
    public void dbInit() {

    }

    @Override
    public boolean isDbInit() {
        return false;
    }
}