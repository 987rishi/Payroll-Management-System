package com.pms.Payroll.Management.System.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Month;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayrollRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payroll_id")
    private Long payrollId;

    @NotNull
    @Column(nullable = false, name = "base_salary")
    private Double baseSalary;

    @Column(name = "pay_date_start")
    private LocalDate payDateStart;

    @Column(name = "pay_date_end")
    private LocalDate payDateEnd;

    @Column(name = "net_salary")
    private Double netSalary;

    private Month month;

    @Column(name = "salary_paid")
    private boolean isSalaryPaid;

    @ManyToOne
    @JoinColumn(name = "employee_id",nullable = false)
    @JsonIgnore
    private Employee employee;

}
