package com.softuni.andrey.s.repository;

import com.softuni.andrey.s.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByUsernameAndEmail( String username, String email);

    Optional<User> findByUsernameAndPassword( String username, String password);
}
