package com.pms.Payroll.Management.System.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Deduction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ded_id")
    private Integer deductionId;

    @Column(name = "total_deduction_type")
    private Double totalDeductionAmount;

    @ManyToMany
    @JoinTable(
            name = "deduction_deduction_type",
            joinColumns = @JoinColumn(name = "d_id"),
            inverseJoinColumns = @JoinColumn(name="dt_id")
            )
    private Set<DeductionType> deductionType;

    @OneToOne
    @JoinColumn(name = "payroll_id")
    private PayrollRecord payrollRecord;
}
