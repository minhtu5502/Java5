package assignment.assignment_java5.repository;

import assignment.assignment_java5.model.AgriculturalProduce;
import assignment.assignment_java5.model.typeEnum.TypeAgriculturalProduce;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAgriculturalProduceRepository extends JpaRepository<AgriculturalProduce, Integer> {
    void deleteByIdIn(List<Integer> id);
    List<AgriculturalProduce> findByType(TypeAgriculturalProduce type);
}
