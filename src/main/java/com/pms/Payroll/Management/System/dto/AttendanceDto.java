package com.pms.Payroll.Management.System.dto;

import com.pms.Payroll.Management.System.models.Conduct;
import com.pms.Payroll.Management.System.models.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Month;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDto {

  private Long attId;

  private Integer totalWorkDays;

  private  Integer totalLeaveDays;

  private Integer employee;

  private Month month;

  private Integer daysPresent;

  private Integer daysOnLeave;

  private Integer daysWithLateEntry;

  private Integer daysWithEarlyExit;

  private Conduct conduct;

  private String remarks;

}
