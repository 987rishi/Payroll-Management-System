package com.pms.Payroll.Management.System.dto;

import com.pms.Payroll.Management.System.models.Designation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRegister {

    @NotNull
    private String employeeFname;
    @NotNull
    private String employeeLname;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String phoneNumber;

    @NotNull
    private Designation designation;

    @NotNull
    private String roleName;

    @NotNull
    private LocalDate contractDurationStart;

    @NotNull
    private LocalDate contractDurationEnd;

    private LocalDate startingDate;

    @NotNull
    private String departmentName;
}
