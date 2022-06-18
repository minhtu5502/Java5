package assignment.assignment_java5.repository;

import assignment.assignment_java5.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISupplierRepository extends JpaRepository<Supplier, Integer> {
    void deleteByIdIn(List<Integer> id);
}
