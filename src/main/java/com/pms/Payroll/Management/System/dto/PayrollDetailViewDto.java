package com.pms.Payroll.Management.System.dto;

import com.pms.Payroll.Management.System.models.AllowanceType;
import com.pms.Payroll.Management.System.models.DeductionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayrollDetailViewDto {

  private Long payrollId;

  private Double baseSalary;

  private LocalDate payDateStart;

  private LocalDate payDateEnd;

  private Double netSalary;

  private Month month;

  private boolean isSalaryPaid;

  Set<AllowanceType> allowances;

  Set<DeductionType> deductions;

}
