package assignment.assignment_java5.service;

import assignment.assignment_java5.model.typeEnum.StatusAgriculturalProduce;
import assignment.assignment_java5.model.typeEnum.TypeAgriculturalProduce;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import assignment.assignment_java5.model.AgriculturalProduce;

import java.math.BigDecimal;
import java.util.List;

public interface AgriculturalProduceService {
    AgriculturalProduce add(AgriculturalProduce agriculturalProduce);

    AgriculturalProduce update(AgriculturalProduce agriculturalProduce);

    List<AgriculturalProduce> getAll();

    AgriculturalProduce getById(Integer id);

    List<AgriculturalProduce> getByType(TypeAgriculturalProduce type);

    Page<AgriculturalProduce> getByPage(int pageNumber, int maxRecord);

    AgriculturalProduce delete(Integer id);

    void deleteByIdIn(Integer[] id);

    Page<AgriculturalProduce> findPaginated(int pageNo, int pageSize);

    void saveEarPhoneToDb(MultipartFile file, String name,  double weight, Integer supplierId, BigDecimal price, int quantity, TypeAgriculturalProduce type, StatusAgriculturalProduce status , String nutrition);

    void updateEarPhoneToDb(MultipartFile file, Integer id, String name, double weight, Integer supplierId, BigDecimal price, int quantity, TypeAgriculturalProduce type, StatusAgriculturalProduce status , String nutrition);
}
