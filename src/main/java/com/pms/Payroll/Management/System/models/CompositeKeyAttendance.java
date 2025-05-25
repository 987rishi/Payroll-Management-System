package com.pms.Payroll.Management.System.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Month;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompositeKeyAttendance implements Serializable {
  Long attId;
  Month month;
  Employee employee;

}
