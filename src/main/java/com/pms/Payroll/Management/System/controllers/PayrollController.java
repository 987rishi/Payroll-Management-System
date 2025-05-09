package com.pms.Payroll.Management.System.controllers;

import com.pms.Payroll.Management.System.dto.PayrollDto;
import com.pms.Payroll.Management.System.service.PayrollService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payroll-management")
public class PayrollController {

  @Autowired
  private PayrollService payrollService;

  @PostMapping("payrolls")
  public ResponseEntity<?> createPayroll(@RequestBody @Valid
                                         PayrollDto payrollDto) {
    return payrollService.createPayroll(payrollDto);
  }

  @GetMapping("payrolls/{payrollId}")
  public ResponseEntity<?> getPayrollById(@PathVariable Long payrollId) {
    return payrollService.getPayrollById(payrollId);
  }

  @GetMapping("payrolls")
  public ResponseEntity<?> getAllPayrolls() {
    return payrollService.getAllPayrolls();
  }

  @PutMapping("payrolls/{payrollId}")
  public ResponseEntity<?> markPaid(@PathVariable Long payrollId,
                                    @RequestParam(name = "paid",
                                            required = true,
                                            defaultValue = "false") boolean paid
                                    ) {
    if(!paid) return new ResponseEntity<>("STATUS IS FALSE", HttpStatus.OK);
    return payrollService.markPaid(payrollId);

  }
}
