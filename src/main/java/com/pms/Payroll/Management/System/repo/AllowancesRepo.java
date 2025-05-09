package com.pms.Payroll.Management.System.repo;

import com.pms.Payroll.Management.System.models.Allowances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllowancesRepo extends JpaRepository<Allowances,Long> {
}
