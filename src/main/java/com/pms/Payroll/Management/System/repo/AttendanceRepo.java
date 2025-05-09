package com.pms.Payroll.Management.System.repo;

import com.pms.Payroll.Management.System.models.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance,Long> {

  @NativeQuery("select * from attendance where employee_id=:employeeId")
  Optional<Attendance> findByEmployeeId(Long employeeId);
}
