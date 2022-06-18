package assignment.assignment_java5.service;

import assignment.assignment_java5.model.Supplier;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SupplierService {
    Supplier add(Supplier supplier);
    Supplier update(Supplier supplier);
    Supplier delete(Integer id);
    Supplier deleteByIdIn(List<Integer> id);
    List<Supplier> getAll();
    Page<Supplier> getSuppliers(int pageNumber, int maxRecord);
}
