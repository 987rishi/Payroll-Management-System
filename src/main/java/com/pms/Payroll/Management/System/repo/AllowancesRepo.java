package com.pms.Payroll.Management.System.repo;

import com.pms.Payroll.Management.System.models.Allowances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AllowancesRepo extends JpaRepository<Allowances,Long> {


  @NativeQuery("select * from allowances where " +
          "payroll_record_payroll_id=:payrollId")
  Optional<Allowances> findByPayrollRecord(Long payrollId);
}
