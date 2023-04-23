package com.resellerapp.service.impl;

import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import com.resellerapp.model.service.OfferServiceModel;
import com.resellerapp.model.service.UserServiceModel;
import com.resellerapp.model.view.OfferViewModel;
import com.resellerapp.repository.ConditionRepository;
import com.resellerapp.repository.OfferRepository;
import com.resellerapp.repository.UserRepository;
import com.resellerapp.service.OfferService;
import com.resellerapp.service.UserService;
import com.resellerapp.util.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final ConditionRepository conditionRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;

    public OfferServiceImpl(OfferRepository offerRepository, ConditionRepository conditionRepository, UserService userService, UserRepository userRepository, ModelMapper modelMapper, LoggedUser loggedUser) {
        this.offerRepository = offerRepository;
        this.conditionRepository = conditionRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }

    @Override
    public void addOrder(OfferServiceModel offerServiceModel) {
        Offer offer = modelMapper.map(offerServiceModel, Offer.class);
        User user = this.userRepository.findById(loggedUser.getId()).orElseThrow();
        offer.setDescription(offerServiceModel.getDescription());
        offer.setPrice(offerServiceModel.getPrice());
        offer.setCondition(this.conditionRepository.findConditionByConditionEnum(offerServiceModel.getConditionEnum()).orElse(null));
        offer.setUser(user);
        this.offerRepository.save(offer);

    }

    @Override
    public List<OfferViewModel> findMyOffers() {
        User user = this.userRepository.findById(loggedUser.getId()).orElseThrow();
        List<OfferViewModel> myOffers = this.offerRepository.findAllByUserId(loggedUser.getId())
                .stream()
                .map(offer -> modelMapper.map(offer, OfferViewModel.class))
                .collect(Collectors.toList());
        return myOffers;
    }

    @Override
    public void removeMyOffer(Long id) {
        this.offerRepository.deleteById(id);
    }

    @Override
    public List<OfferViewModel> findOtherOffers() {

        User user = this.userRepository.findById(loggedUser.getId()).orElseThrow();
        User notLoggedUser = this.userRepository.findByIdNot(loggedUser.getId()).orElse(null);
        List<OfferViewModel> otherOffers = this.offerRepository.findAllByUserNot(user)
                    .stream()
                    .map(offer -> modelMapper.map(offer, OfferViewModel.class))
               .collect(Collectors.toList());

        return otherOffers;

    }

    @Override
    @Transactional
    public OfferViewModel buyOffer(Long id) {

            Offer offer = this.offerRepository.findById(id).orElseThrow();
        User notLoggedUser = this.userRepository.findByIdNot(loggedUser.getId()).orElseThrow();
            User user = this.userRepository.findById(this.loggedUser.getId()).orElseThrow();
        List<Offer> boughtOffers = this.offerRepository.findAllByUserNot(user)
                .stream()
                .map(offer1 -> modelMapper.map(offer1, Offer.class))
                .collect(Collectors.toList());
        for (Offer offer1: boughtOffers){
            if (offer1.equals(offer)){
                offer1.setUser(null);

            }}
        offer.setUser(user);
        user.getBoughtOffers().add(offer);

        offerRepository.save(offer);
        userRepository.save(user);
        return modelMapper.map(offer,OfferViewModel.class);
        }


}



   /* @Override
    public List<OfferViewModel> buyOffer(Long id) {
        User notLoggedUser = this.userRepository.findByIdNot(loggedUser.getId()).orElseThrow();
        User user = this.userRepository.findById(loggedUser.getId()).orElseThrow();
        List<OfferViewModel> otherOffers = this.offerRepository.findAllByUserNot(user)
                .stream()
                .map(offer -> modelMapper.map(offer, OfferViewModel.class))
                .collect(Collectors.toList());
        Offer offer = this.offerRepository.findById(id).orElse(null);
        otherOffers.remove(offer);

       user.getBoughtOffers().add(offer);
        this.userRepository.save(user);
         List<OfferViewModel> boughtOffers = user.getBoughtOffers().stream().map(offer1 ->
                 modelMapper.map(offer1.getUser().getBoughtOffers(), OfferViewModel.class)).collect(Collectors.toList());
         return  boughtOffers;



    }*/

   /* public List<OfferViewModel> getOtherOffers() {

        User user = this.userRepository.findById(loggedUser.getId()).orElseThrow();
        User notLoggedUser = this.userRepository.findByIdNot(loggedUser.getId()).orElseThrow();
        List<Offer> offer = Collections.singletonList(notLoggedUser.getOffer());
        List<OfferViewModel> otherOffers = this.offerRepository.findAllByUserNot(user)
                .stream()
                .map(offer -> modelMapper.map(offer, OfferViewModel.class))
                .collect(Collectors.toList());


        return otherOffers;

    }*/

