package com.example.modelmapper.repositories;

import com.example.modelmapper.dtos.UserLoginDto;
import com.example.modelmapper.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndPassword(String email, String password);
    @Modifying
    @Transactional
    @Query("update User u set u.isLogged = true")
    void logIn(UserLoginDto userLoginDto);

    @Modifying
    @Transactional
    @Query("update User u set u.isLogged = false")
    void logOut(UserLoginDto userLoginDto);

}
