package com.spotifyplaylistapp.repository;

import com.spotifyplaylistapp.model.entity.Song;
import com.spotifyplaylistapp.model.entity.StyleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    Optional<Song> findByTitle(String title);
    Optional<List<Song>> findAllByStyle_Name(StyleEnum styleEnum);

    Set<Song> findByStyle_Name(StyleEnum styleEnum);

    @Query("select s, u from Song s join s.users u on u.id = :id")
    Set<Song> findAllByUserId(@Param("id") Long id);

   // @Query("SELECT sum(s.duration) from Song s join s.users u on u.id = :id")
  // String totalDurationOfPlaylistByUserId(Long id);
}
