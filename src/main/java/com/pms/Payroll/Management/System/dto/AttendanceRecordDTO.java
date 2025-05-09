package com.pms.Payroll.Management.System.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pms.Payroll.Management.System.models.Attendance;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceRecordDTO {

    @NotNull
    private LocalDate dateOfRecord;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime lateEntry;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime earlyExit;

    private Integer overtime;//Hrs

    private String remarks;

    @NotNull
    private Long attendance;
}
