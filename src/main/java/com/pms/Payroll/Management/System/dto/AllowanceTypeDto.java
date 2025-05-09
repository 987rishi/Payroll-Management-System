package com.pms.Payroll.Management.System.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllowanceTypeDto {

  @NotNull
  private String allowanceTypeName;
  @NotNull
  private Double amount;
}
