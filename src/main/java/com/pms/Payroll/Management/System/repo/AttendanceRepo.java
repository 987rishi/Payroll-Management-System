package com.pms.Payroll.Management.System.repo;

import com.pms.Payroll.Management.System.models.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import java.time.Month;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance,Long> {

  @NativeQuery("select * from attendance where employee_id=:employeeId")
  List<Attendance> findAllByEmployeeId(Long employeeId);

  @NativeQuery("select * from attendance where employee_id=:employeeId and " +
          "month=:month ")
  Attendance findByEmpIdAndMonth(Long employeeId, Integer month);
}
