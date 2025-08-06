package Back_endSpringBoot.domain.repository;

import Back_endSpringBoot.domain.model.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionRepository extends JpaRepository<Collection,Long> {
}
