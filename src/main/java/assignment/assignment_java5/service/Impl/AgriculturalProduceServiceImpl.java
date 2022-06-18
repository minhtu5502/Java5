package assignment.assignment_java5.service.Impl;

import assignment.assignment_java5.model.AgriculturalProduce;
import assignment.assignment_java5.model.Supplier;
import assignment.assignment_java5.model.typeEnum.StatusAgriculturalProduce;
import assignment.assignment_java5.model.typeEnum.TypeAgriculturalProduce;
import assignment.assignment_java5.repository.IAgriculturalProduceRepository;
import assignment.assignment_java5.repository.ISupplierRepository;
import assignment.assignment_java5.service.AgriculturalProduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Service
public class AgriculturalProduceServiceImpl implements AgriculturalProduceService {
    @Autowired
    private IAgriculturalProduceRepository agriculturalProduceRepository;
    @Autowired
    private ISupplierRepository supplierRepository;

    @Override
    public AgriculturalProduce add(AgriculturalProduce agriculturalProduce) {
        agriculturalProduce.setId(null);
        return agriculturalProduceRepository.save(agriculturalProduce);
    }

    @Override
    public AgriculturalProduce update(AgriculturalProduce agriculturalProduce) {
        Integer id = agriculturalProduce.getId();
        if (id != null) {
            Optional<AgriculturalProduce> a = agriculturalProduceRepository.findById(id);
            if (a.isPresent()) {
                agriculturalProduceRepository.save(agriculturalProduce);
                return a.get();
            }
        }
        return null;
    }

    @Override
    public List<AgriculturalProduce> getAll() {
        return agriculturalProduceRepository.findAll();
    }

    @Override
    public AgriculturalProduce getById(Integer id) {
        return agriculturalProduceRepository.findById(id).get();
    }

    @Override
    public List<AgriculturalProduce> getByType(TypeAgriculturalProduce type) {

        return agriculturalProduceRepository.findByType(type);
    }

    @Override
    public Page<AgriculturalProduce> getByPage(int pageNumber, int maxRecord) {
        Pageable pageable = PageRequest.of(pageNumber - 1, maxRecord);
        return this.agriculturalProduceRepository.findAll(pageable);
    }

    @Override
    public AgriculturalProduce delete(Integer id) {
        if (id != null) {
            Optional<AgriculturalProduce> e = agriculturalProduceRepository.findById(id);
            if (e.isPresent()) {
                agriculturalProduceRepository.deleteById(id);
                return e.get();
            }
        }
        return null;
    }

    @Override
    public void deleteByIdIn(Integer[] id) {
        agriculturalProduceRepository.deleteByIdIn(Arrays.asList(id));
    }

    @Override
    public Page<AgriculturalProduce> findPaginated(int pageNo, int pageSize) {
        return null;
    }

    @Override
    public void saveEarPhoneToDb(MultipartFile file, String name, double weight, Integer supplierId, BigDecimal price, int quantity, TypeAgriculturalProduce type, StatusAgriculturalProduce status, String nutrition) {
        AgriculturalProduce agriculturalProduce = new AgriculturalProduce();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains("..")) {
            System.out.println("not a a valid file");
        }
        try {
            agriculturalProduce.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Supplier supplier = supplierRepository.findById(supplierId).get();
        agriculturalProduce.setName(name);
        agriculturalProduce.setWeight(weight);
        agriculturalProduce.setSupplier(supplier);
        agriculturalProduce.setPrice(price);
        agriculturalProduce.setQuantity(quantity);
        agriculturalProduce.setCreatedDate(new Date());
        agriculturalProduce.setType(type);
        agriculturalProduce.setStatus(status);
        agriculturalProduce.setNutrition(nutrition);
        agriculturalProduce.setId(null);
        agriculturalProduceRepository.save(agriculturalProduce);
    }

    @Override
    public void updateEarPhoneToDb(MultipartFile file, Integer id, String name, double weight, Integer supplierId, BigDecimal price, int quantity, TypeAgriculturalProduce type, StatusAgriculturalProduce status, String nutrition) {
        AgriculturalProduce agriculturalProduce = new AgriculturalProduce();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (file.isEmpty()) {
            agriculturalProduce.setImage(agriculturalProduceRepository.findById(id).get().getImage());
        } else {
            if (fileName.contains("..")) {
                System.out.println("not a a valid file");
            }
            try {
                agriculturalProduce.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        agriculturalProduce.setName(name);
        agriculturalProduce.setWeight(weight);
        agriculturalProduce.setSupplier(supplierRepository.findById(supplierId).get());
        agriculturalProduce.setPrice(price);
        agriculturalProduce.setQuantity(quantity);
        agriculturalProduce.setCreatedDate(agriculturalProduceRepository.findById(id).get().getCreatedDate());
        agriculturalProduce.setType(type);
        agriculturalProduce.setStatus(status);
        agriculturalProduce.setNutrition(nutrition);
        agriculturalProduce.setId(id);
        agriculturalProduceRepository.save(agriculturalProduce);
    }
}
