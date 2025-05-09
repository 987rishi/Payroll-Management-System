package com.pms.Payroll.Management.System.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appeals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_id")
    private Integer applicationId;

    @NotNull
    @Column(name = "app_sub")
    private String applicationSubject;

    @NotNull
    @Column(name = "app_body")
    private String applicationBody;

    @ManyToOne
    @JoinColumn(name = "sent_to_employee_id")
    private Employee sentToEmployee;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;



    @Column(name = "exemption_start_date")
    private LocalDate exemptionStartDate;

    @Column(name = "exemption_end_date")
    private LocalDate exemptionEndDate;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;


    @Column(name = "initiation_date")
    private LocalDate initiationDate;

    @Lob
    @Column(name = "attachment")
    private byte[] attachment;

    @ManyToOne
    @JoinColumn(name="approved_by_employee_id")
    private Employee approvedBy;

    @Column(name = "date_of_approval")
    private LocalDate dateOfApproval;

    @Column(name = "accepted")
    private boolean accepted;

    @Column(name = "remarks")
    private String remarks;

    @Enumerated(EnumType.STRING)
    @Column(name = "approval_status")
    private ApprovalStatus approvalStatus;
}
