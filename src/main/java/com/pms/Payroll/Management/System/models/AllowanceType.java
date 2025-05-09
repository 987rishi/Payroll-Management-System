package com.pms.Payroll.Management.System.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllowanceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "at_id")
    private Integer allowanceTypeId;

    @NotNull
    @Column(name = "at")
    private String allowanceTypeName;
    @NotNull
    private Double amount;
}
