package com.pms.Payroll.Management.System.repo;

import com.pms.Payroll.Management.System.models.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;

import java.util.List;

public interface AttendanceRecordRepo extends JpaRepository<AttendanceRecord,Integer> {

  @NativeQuery("select * from attendance_record ar where ar.att_id=:attId")
  List<AttendanceRecord> findAllByAttendanceId(Long attId);
}
