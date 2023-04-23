package com.resellerapp.repository;

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
public interface UserRepository extends JpaRepository<User, Long> {
Optional<User> findById(Long id);
Optional<User> findByUsernameAndPassword(String username, String password);
    Optional<User> findByUsername(String username);

   Optional<User> findByIdNot(Long id);

    @Query("select b.boughtOffers  from User as b where b.id= :id")
    Set<Offer> getUserBoughtOffers(@Param("id") Long id);

}
