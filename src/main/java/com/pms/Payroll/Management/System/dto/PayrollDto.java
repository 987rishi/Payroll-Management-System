package com.pms.Payroll.Management.System.dto;

import com.pms.Payroll.Management.System.models.Employee;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayrollDto {

  private Integer employeeId;

  private Double baseSalary;

  private LocalDate payDateStart;

  private LocalDate payDateEnd;

  private List<String> allowancesTypesNames;

  private List<String> deductionTypesNames;

  private Month month;

}
