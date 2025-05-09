package com.pms.Payroll.Management.System.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Allowances {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "allowances_id")
    private Long allowancesId;

    @Column(name = "total_allowance_amount")
    private Double totalAllowanceAmount;

    @ManyToMany
    @JoinTable(
            name="allowance_allowance_type",
            joinColumns = @JoinColumn(name="allowances_id"),
            inverseJoinColumns = @JoinColumn(name="at_id")
    )
    private Set<AllowanceType> allowanceType;

    @OneToOne
    private PayrollRecord payrollRecord;
}
