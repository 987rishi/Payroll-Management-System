package com.pms.Payroll.Management.System.repo;

import com.pms.Payroll.Management.System.models.Deduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeductionRepo extends JpaRepository<Deduction,Long> {

  @NativeQuery("select * from deduction where payroll_id=:payrollId")
  Optional<Deduction> findByPayrollRecord(Long payrollId);
}
