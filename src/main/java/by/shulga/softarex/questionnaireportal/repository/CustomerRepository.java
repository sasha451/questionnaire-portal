package by.shulga.softarex.questionnaireportal.repository;

import by.shulga.softarex.questionnaireportal.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
