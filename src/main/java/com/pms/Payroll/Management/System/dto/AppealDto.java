package com.pms.Payroll.Management.System.dto;


import com.pms.Payroll.Management.System.models.ApprovalStatus;
import com.pms.Payroll.Management.System.models.Employee;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppealDto {

  private Integer applicationId;

  @NotNull
  private String applicationSubject;

  @NotNull
  private String applicationBody;

  @NotNull
  private Integer sentToEmployee;

  @NotNull
  private Integer employee;

  private LocalDate exemptionStartDate;

  private LocalDate exemptionEndDate;

  private LocalDateTime timestamp;

  private LocalDate initiationDate;

  private byte[] attachment;

  private Integer approvedBy;

  private LocalDate dateOfApproval;

  private boolean accepted;

  private String remarks;

  @Enumerated(EnumType.STRING)
  private ApprovalStatus approvalStatus;
}
