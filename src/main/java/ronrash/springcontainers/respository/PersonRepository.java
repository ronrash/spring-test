package ronrash.springcontainers.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import ronrash.springcontainers.model.entity.PersonEntity;

public interface PersonRepository extends JpaRepository<PersonEntity,Long> {
    Optional<PersonEntity> findByIdentityDetail(String identity);

    Optional<List<PersonEntity>> findByAge(int age);
}
