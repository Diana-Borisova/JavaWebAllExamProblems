package com.resellerapp.service;

import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import com.resellerapp.model.service.OfferServiceModel;
import com.resellerapp.model.service.UserServiceModel;
import com.resellerapp.model.view.OfferViewModel;
import com.resellerapp.repository.OfferRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface OfferService {

   void addOrder(OfferServiceModel offerServiceModel);

   List<OfferViewModel> findMyOffers();

   void removeMyOffer(Long id);

   List<OfferViewModel> findOtherOffers();



  // List<OfferViewModel> buyOffer(Long id);


   public OfferViewModel buyOffer(Long id);


}
