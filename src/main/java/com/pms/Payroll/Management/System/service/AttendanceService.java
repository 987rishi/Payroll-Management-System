package com.pms.Payroll.Management.System.service;

import com.pms.Payroll.Management.System.dto.AttendanceDto;
import com.pms.Payroll.Management.System.dto.AttendanceRecordDTO;
import com.pms.Payroll.Management.System.helpers.Utility;
import com.pms.Payroll.Management.System.mappers.AttendanceMapper;
import com.pms.Payroll.Management.System.models.Attendance;
import com.pms.Payroll.Management.System.models.AttendanceRecord;
import com.pms.Payroll.Management.System.repo.AttendanceRecordRepo;
import com.pms.Payroll.Management.System.repo.AttendanceRepo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class AttendanceService {
  @Autowired
  private AttendanceRecordRepo attendanceRecordRepo;
  @Autowired
  private AttendanceRepo attendanceRepo;
  @Autowired
  private AttendanceMapper attendanceMapper;

  @Transactional
  public ResponseEntity<?> mark(@Valid AttendanceRecordDTO attendanceRecord) {

    LocalDate dateOfRecord;
    if(attendanceRecord.getDateOfRecord() == null)
      dateOfRecord = LocalDate.now();
    else
      dateOfRecord = attendanceRecord.getDateOfRecord();


    Attendance attendance = Utility.findOrThrow("ATTENDANCE",
            attendanceRepo,attendanceRecord.getAttendance());

    AttendanceRecord newRecord = new AttendanceRecord();

    newRecord.setAttendance(attendance);
    newRecord.setDateOfRecord(dateOfRecord);
    newRecord.setOvertime(attendanceRecord.getOvertime());
    newRecord.setRemarks(attendanceRecord.getRemarks());
    newRecord.setLateEntry(attendanceRecord.getLateEntry());
    newRecord.setEarlyExit(attendanceRecord.getEarlyExit());


    attendance.setDaysPresent(attendance.getDaysPresent() + 1);
    if(attendanceRecord.getEarlyExit() != null)
      attendance.setDaysWithEarlyExit(attendance.getDaysWithEarlyExit() + 1);
    if (attendanceRecord.getLateEntry() != null)
      attendance.setDaysWithLateEntry(attendance.getDaysWithLateEntry() + 1);
    // Also update the late entry or eary exit and number of days present
    // attributes in Attendance

    try {
      attendanceRepo.save(attendance);
      return new ResponseEntity<>(attendanceRecordRepo.save(newRecord),
              HttpStatus.CREATED);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  public ResponseEntity<?> getAttendanceByAttendanceId(
          @Positive
          Long attendanceId) {

    AttendanceDto attendanceDto = attendanceMapper.toDto(attendanceRepo
            .findById(attendanceId)
            .orElseThrow(() ->
                    new NoSuchElementException(
                            "ATTENDANCE DOES NOT EXIST CORRESPONDING TO ID"
                            + attendanceId
                    )
            )
    );
    return new ResponseEntity<>(attendanceDto,HttpStatus.OK);
  }

  public ResponseEntity<?> getAllAttendances() {
    List<AttendanceDto> attendanceDtos = attendanceRepo
            .findAll()
            .stream()
            .map(attendanceMapper::toDto)
            .collect(Collectors.toList());
    return  new ResponseEntity<>(attendanceDtos ,HttpStatus.OK);
  }

  public ResponseEntity<?> getAttendanceByEmployeeId(Long employeeId) {

    AttendanceDto attendanceDto =
            attendanceMapper.toDto(attendanceRepo
                    .findByEmployeeId(employeeId)
                    .orElseThrow(() ->
                            new NoSuchElementException("ATTENDANCE DOES NOT " +
                                    "EXIST CORRESPONDING TO EMPLOYEE ID :" + employeeId))
            );

    return new ResponseEntity<>(attendanceDto, HttpStatus.OK);
  }
}
