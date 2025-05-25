package com.pms.Payroll.Management.System.dto;

import com.pms.Payroll.Management.System.models.Conduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneratePayslipsDto {
  //
//  { empId: 'EMP001', empName: 'Alice Smith', dept: 'HR', attendancePercent: 95, currentPaySalary: 6000, payPeriod: '2024-07', conduct: 'Good', remarks: '', salaryPaid: false },
//  { empId: 'EMP002', empName: 'Bob Johnson', dept: 'HR', attendancePercent: 98, currentPaySalary: 6200, payPeriod: '2024-07', conduct: 'Excellent', remarks: 'Top performer', salaryPaid: true },
//          ];

  private Long empId;
  private String empName;
  private String dept;
//  private Double attendancePercent;
  private Double currentPaySalary;
  private Date payDateStart;
  private Date payDateEnd;
  private String conduct;
  private String remarks;
  private Boolean salaryPaid;
  private Long payrollId;
}
