package com.pms.Payroll.Management.System.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long employeeId;
  @NotNull
  private String employeeFname;
  @NotNull
  private String employeeLname;
  @Email
  private String email;
  @NotNull
  private String phoneNumber;
  @NotNull
  @Enumerated(EnumType.STRING)
  private Designation designation;
  @ManyToOne
  @JoinColumn(name = "role_id")
  private Role role;

  @JsonIgnore
  private LocalDate contractDurationStart;

  @JsonIgnore
  private LocalDate contractDurationEnd;

  @JsonIgnore
  private LocalDate startingDate;

  @ManyToOne
  @JoinColumn(name="department_id",nullable = false)
  private Department department;
}
