package ua.kpi.tef.toys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kpi.tef.toys.pojo.Toy;
import ua.kpi.tef.toys.pojo.enums.ToySize;
import ua.kpi.tef.toys.pojo.enums.Kind;

import java.util.List;
import java.util.Optional;

@Repository
public interface ToysRepository extends JpaRepository<Toy, Long> {
    Optional<Toy> findToyById(Long id);
    List<Toy> findToyByKind(Kind kind);
    List<Toy> findToyByToySize(ToySize size);
}
