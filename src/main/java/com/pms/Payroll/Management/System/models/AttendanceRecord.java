package com.pms.Payroll.Management.System.models;

import jakarta.annotation.Nullable;
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
@Table(indexes = {
        @Index(name="idx_att_id",columnList = "att_id")
})
public class AttendanceRecord {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "att_record_id")
    private Integer recordId;

    @NotNull
    @Column(nullable = false, name = "date_of_record")
    private LocalDate dateOfRecord;

    @Column(name = "late_entry")
    private LocalDateTime lateEntry;

    @Column(name = "early_exit")
    private LocalDateTime earlyExit;

    private Integer overtime;//Hrs

    private String remarks;

    @ManyToOne
    @JoinColumn(name="att_id")
    private Attendance attendance;
}
