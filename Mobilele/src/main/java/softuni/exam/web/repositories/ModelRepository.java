package softuni.exam.web.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.web.domain.entities.Model;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
}
