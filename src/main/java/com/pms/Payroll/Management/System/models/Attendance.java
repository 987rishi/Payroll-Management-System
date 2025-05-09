package com.pms.Payroll.Management.System.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.Month;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "att_id")
    private Long attId;

    @Column(name = "total_work_days")
    private Integer totalWorkDays = 0;

    @Column(name = "total_leave_days")
    private  Integer totalLeaveDays = 0;

    @ManyToOne
    @JoinColumn(name = "employee_id",nullable = false)
    private Employee employee;

    @Column(name = "month",nullable = false)
    private Month month;

    @Column(name = "days_present")
    private Integer daysPresent = 0;

    @Column(name = "days_on_leave")
    private Integer daysOnLeave = 0;

    @Column(name = "num_of_days_with_late_entry")
    private Integer daysWithLateEntry = 0;

    @Column(name = "num_of_days_early_exit")
    private Integer daysWithEarlyExit = 0;

    @Column(name = "conduct")
    private Conduct conduct;

    @Column(name = "remarks")
    private String remarks;
}
