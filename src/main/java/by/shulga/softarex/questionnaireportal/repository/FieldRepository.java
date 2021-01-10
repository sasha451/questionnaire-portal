package by.shulga.softarex.questionnaireportal.repository;

import by.shulga.softarex.questionnaireportal.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FieldRepository extends JpaRepository<Field, Long> {

    Optional<Field> findByLabel(String label);
}
