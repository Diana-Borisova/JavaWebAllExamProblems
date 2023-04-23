package com.spotifyplaylistapp.repository;

import com.spotifyplaylistapp.model.entity.Song;
import com.spotifyplaylistapp.model.entity.Style;
import com.spotifyplaylistapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

Optional<User> findByUsernameAndPassword(String username, String password);


}
