package com.pms.Payroll.Management.System.repo;

import com.pms.Payroll.Management.System.models.PayrollRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayrollRecordRepo extends JpaRepository<PayrollRecord,Long> {
}
