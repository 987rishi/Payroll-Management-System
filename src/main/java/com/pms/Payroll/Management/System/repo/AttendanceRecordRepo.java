package com.pms.Payroll.Management.System.repo;

import com.pms.Payroll.Management.System.models.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRecordRepo extends JpaRepository<AttendanceRecord,Integer> {
}
