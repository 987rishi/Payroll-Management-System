package com.pms.Payroll.Management.System.repo;

import com.pms.Payroll.Management.System.models.AllowanceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface AllowanceTypeRepo extends JpaRepository<AllowanceType,
        Integer> {
  List<AllowanceType> findAllByAllowanceTypeNameIn(List<String> allowancesTypesNames);

  @NativeQuery("select * from allowance_type where at=:allowanceName")
  Optional<AllowanceType> findByAllowanceTypeName(String allowanceName);
}
