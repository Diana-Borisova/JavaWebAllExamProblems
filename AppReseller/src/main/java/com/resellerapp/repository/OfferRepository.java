package com.resellerapp.repository;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.ConditionEnum;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

List<Offer> findAllByUserId(Long id);

/*@Query("select o from Offer o join User u on u.id = o.user.id where u.id not in :id")*/
List<Offer> findAllByUserNot(User user);



}
