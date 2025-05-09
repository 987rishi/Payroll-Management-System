package com.pms.Payroll.Management.System.repo;

import com.pms.Payroll.Management.System.models.DeductionType;
import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface DeductionTypeRepo extends JpaRepository<DeductionType,Integer> {
  Optional<Set<DeductionType>> findAllByDeductionTypeNameIn(List<String> deductionTypesNames);

  Optional<DeductionType> findByDeductionTypeName(@Valid String deductionTypeName);
}
