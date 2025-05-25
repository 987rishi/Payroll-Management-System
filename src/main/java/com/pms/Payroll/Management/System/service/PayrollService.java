package com.pms.Payroll.Management.System.service;

import com.pms.Payroll.Management.System.dto.GeneratePayslipsDto;
import com.pms.Payroll.Management.System.dto.PayrollDetailViewDto;
import com.pms.Payroll.Management.System.dto.PayrollDto;
import com.pms.Payroll.Management.System.dto.ViewPayrollPageDto;
import com.pms.Payroll.Management.System.models.*;
import com.pms.Payroll.Management.System.repo.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PayrollService {

  @Autowired
  private PayrollRecordRepo payrollRecordRepo;

  @Autowired
  private EmployeeRepo employeeRepo;

  @Autowired
  private AllowanceTypeRepo allowanceTypeRepo;

  @Autowired
  private DeductionTypeRepo deductionTypeRepo;

  @Autowired
  private AllowancesRepo allowancesRepo;

  @Autowired
  private DeductionRepo deductionRepo;

  @Autowired
  private AttendanceRepo attendanceRepo;

  @Autowired
  private EmployeeService employeeService;



  private Logger logger = LoggerFactory.getLogger(PayrollService.class);

  public ResponseEntity<?> createPayroll(@Valid PayrollDto payrollDto) {

    Allowances allowances = new Allowances();
    Deduction deduction = new Deduction();
    PayrollRecord payrollRecord = buildPayrollRecord(payrollDto,allowances,deduction);

    logger.info("Payroll Record {}", payrollRecord);

    logger.info("ALLOWANCES {}", allowances);
    logger.info("DEDUCTIONS {}", deduction);


    try {
      PayrollRecord temp = payrollRecordRepo.save(payrollRecord);
      allowancesRepo.save(allowances);
      deductionRepo.save(deduction);
      return new ResponseEntity<>(temp,
              HttpStatus.CREATED);

    } catch (RuntimeException e) {
      throw new RuntimeException(e);
    }
  }

  private PayrollRecord buildPayrollRecord(@Valid PayrollDto payrollDto, Allowances allowances, Deduction deduction) {

    Employee employee = employeeRepo.findById(payrollDto
            .getEmployeeId())
            .orElseThrow(() ->
                    new NoSuchElementException(String
                            .format("EMPLOYEE WITH ID %d DOES NOT EXIST",
                                    payrollDto
                                            .getEmployeeId())));


    Set<AllowanceType> allowanceTypeList =
            allowanceTypeRepo
                    .findAllByAllowanceTypeNameIn(
                            payrollDto
                                    .getAllowancesTypesNames())
//                    .orElseThrow(() ->
//                            new IllegalArgumentException(String
//                                    .format("ALLOWANCES TYPES NAMES ARE " +
//                                            "EITHER INVALID OR DOES NOT " +
//                                            "EXIST:\n%s",
//                                            payrollDto
//                                                    .getAllowancesTypesNames().toString() ))
                    .stream().collect(Collectors.toSet());

    Set<DeductionType> deductionTypeList =
            deductionTypeRepo
                    .findAllByDeductionTypeNameIn(
                            payrollDto
                                    .getDeductionTypesNames())
                    .orElseThrow(() ->
                            new IllegalArgumentException(String
                                    .format("DEDUCTIONS TYPES NAMES ARE " +
                                                    "EITHER INVALID OR DOES NOT " +
                                                    "EXIST:\n%s",
                                            payrollDto
                                                    .getDeductionTypesNames().toString() )));


    allowances.setAllowanceType(allowanceTypeList);
    deduction.setDeductionType(deductionTypeList);


    Double totalAmountAllowances = allowanceTypeList
            .stream()
            .mapToDouble(AllowanceType::getAmount)
            .sum();

    Double totalAmountDeductions = deductionTypeList
            .stream()
            .mapToDouble(DeductionType::getAmount)
            .sum();

    logger.info("TOTAL ALLOWANCES AMOUNT = {}, TOTAL DEDUCTIONS AMOUNT= {}",
            totalAmountAllowances, totalAmountDeductions);

    allowances.setTotalAllowanceAmount(totalAmountAllowances);
    deduction.setTotalDeductionAmount(totalAmountDeductions);

    PayrollRecord record = new PayrollRecord();

    record.setEmployee(employee);
    record.setMonth(payrollDto.getMonth());
    record.setBaseSalary(payrollDto.getBaseSalary());
    record.setPayDateStart(payrollDto.getPayDateStart());
    record.setPayDateEnd(payrollDto.getPayDateEnd());
    //CALCULATE NET SALARY
    record.setNetSalary(payrollDto.getBaseSalary()-(totalAmountAllowances - totalAmountDeductions));
    record.setSalaryPaid(false);

    allowances.setPayrollRecord(record);
    deduction.setPayrollRecord(record);

    return record;
  }

  public ResponseEntity<?> getPayrollById(Long payrollId) {
    return new ResponseEntity<>(payrollRecordRepo
            .findById(payrollId)
            .orElseThrow(() ->
                    new NoSuchElementException(String
                            .format("PAYROLL WITH ID %d DOES NOT EXIST",
                                    payrollId))),
            HttpStatus.OK
    );
  }

  public ResponseEntity<?> getAllPayrolls() {
    return new ResponseEntity<>(payrollRecordRepo.findAll(), HttpStatus.OK);
  }

  public ResponseEntity<?> markPaid(Long payrollId) {
    PayrollRecord payrollRecord = payrollRecordRepo
            .findById(payrollId)
            .orElseThrow(() ->
                    new NoSuchElementException(String
                            .format("PAYROLL WITH ID %d DOES NOT EXIST",
                                    payrollId)));

    payrollRecord.setSalaryPaid(true);

    try {
      return new ResponseEntity<>(payrollRecordRepo.save(payrollRecord),
              HttpStatus.OK);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public ResponseEntity<?> getPayrollsByEmpEmail(@Email String empEmail) {

    List<PayrollRecord> empPayrolls =
            payrollRecordRepo.findAllByEmpEmail(empEmail);

    return new ResponseEntity<>(empPayrolls, HttpStatus.OK);
  }

  public ResponseEntity<?> getPayrollDetailsById(Long payrollId) {
    PayrollRecord payrollRecord = payrollRecordRepo
            .findById(payrollId)
            .orElseThrow(() -> new NoSuchElementException(
                    "PAYROLL RECORD DOES NOT EXIST CORRESPONDING TO ID:" + payrollId)
            );

    PayrollDetailViewDto payrollView = new PayrollDetailViewDto();

    payrollView.setPayrollId(payrollRecord.getPayrollId());
    payrollView.setBaseSalary(payrollRecord.getBaseSalary());
    payrollView.setNetSalary(payrollRecord.getNetSalary());
    payrollView.setMonth(payrollRecord.getMonth());
    payrollView.setSalaryPaid(payrollRecord.isSalaryPaid());
    payrollView.setPayDateEnd(payrollRecord.getPayDateEnd());
    payrollView.setPayDateStart(payrollRecord.getPayDateStart());

    Allowances allowances = allowancesRepo
            .findByPayrollRecord(payrollRecord.getPayrollId())
            .orElseThrow(() -> new NoSuchElementException(
                    "NO ALLOWANCES CORRESPONDING TO PAYROLL ID:" + payrollId
            ));
    Deduction deduction = deductionRepo
            .findByPayrollRecord(payrollRecord.getPayrollId())
            .orElseThrow(() -> new NoSuchElementException(
                    "NO DEDUCTIONS CORRESPONDING TO PAYROLL ID:" + payrollId
            ));

    payrollView.setAllowances(allowances.getAllowanceType());
    payrollView.setDeductions(deduction.getDeductionType());

    return new ResponseEntity<>(payrollView, HttpStatus.OK);


  }

  //
//  { empId: 'EMP001', empName: 'Alice Smith', dept: 'HR', attendancePercent: 95, currentPaySalary: 6000, payPeriod: '2024-07', conduct: 'Good', remarks: '', salaryPaid: false },
//  { empId: 'EMP002', empName: 'Bob Johnson', dept: 'HR', attendancePercent: 98, currentPaySalary: 6200, payPeriod: '2024-07', conduct: 'Excellent', remarks: 'Top performer', salaryPaid: true },
//          ];

  public ResponseEntity<?> getAllEmployeePayrollsOfDeptByEmpEmail(String empEmail) {
    Employee employee = employeeService.getEmployeeByEmail(empEmail);

    List<GeneratePayslipsDto> generatePayslipDtos;
    if (Objects.equals(employee.getRole().getRoleName(), "FINANCE_MANAGER")) {
      generatePayslipDtos = payrollRecordRepo.findAllGeneratePayslipsForFM(employee.getRole().getRank());
      logger.info("ALO");
    }
    else {
      generatePayslipDtos =
              payrollRecordRepo.findAllGeneratePayslipsView(employee.getDepartment().getDeptId(), employee.getRole().getRank());
    }


    return new ResponseEntity<>(generatePayslipDtos, HttpStatus.OK);
  }
}
