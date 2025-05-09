package com.pms.Payroll.Management.System.repo;

import com.pms.Payroll.Management.System.models.Deduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeductionRepo extends JpaRepository<Deduction,Long> {
}
