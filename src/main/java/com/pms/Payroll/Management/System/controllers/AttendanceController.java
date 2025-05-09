package com.pms.Payroll.Management.System.controllers;

import com.pms.Payroll.Management.System.dto.AttendanceRecordDTO;
import com.pms.Payroll.Management.System.service.AttendanceService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attendance-management")
public class AttendanceController {
  @Autowired
  private AttendanceService attendanceService;

  @PostMapping("attendances")
  public ResponseEntity<?> markAttendance(
          @RequestBody
          @Valid
          AttendanceRecordDTO attendanceRecord){
    return attendanceService.mark(attendanceRecord);
  }

  @GetMapping("attendances/{attendanceId}")
  public ResponseEntity<?> getAttendanceById(
          @Positive
          @PathVariable
          Long attendanceId) {

    return attendanceService.getAttendanceByAttendanceId(attendanceId);
  }

  @GetMapping("attendances")
  public ResponseEntity<?> getAllAttendances() {
    return attendanceService.getAllAttendances();
  }

  @GetMapping("attendances/emp/{employeeId}")
  public ResponseEntity<?> getAttendanceByEmployeeId(@PathVariable Long employeeId) {
    return attendanceService.getAttendanceByEmployeeId(employeeId);
  }


}
