package assignment.assignment_java5.service.Impl;

import assignment.assignment_java5.model.AgriculturalProduce;
import assignment.assignment_java5.model.Supplier;
import assignment.assignment_java5.repository.ISupplierRepository;
import assignment.assignment_java5.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    ISupplierRepository supplierRepository;
    @Override
    public Supplier add(Supplier supplier) {
        supplier.setId(null);
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier update(Supplier supplier) {
        if (supplier != null) {
            Optional<Supplier> e = supplierRepository.findById(supplier.getId());
            if (e.isPresent()) {
                supplierRepository.save(supplier);
                return e.get();
            }
        }
        return null;
    }

    @Override
    public Supplier delete(Integer id) {
        return null;
    }

    @Override
    public Supplier deleteByIdIn(List<Integer> id) {
        return null;
    }

    @Override
    public List<Supplier> getAll() {
        return supplierRepository.findAll();
    }

    @Override
    public Page<Supplier> getSuppliers(int pageNumber, int maxRecord) {
        Pageable pageable = PageRequest.of(pageNumber - 1, maxRecord);
        return this.supplierRepository.findAll(pageable);
    }
}
