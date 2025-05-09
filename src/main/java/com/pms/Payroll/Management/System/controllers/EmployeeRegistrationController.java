package com.pms.Payroll.Management.System.controllers;

import com.pms.Payroll.Management.System.dto.EmployeeRegister;
import com.pms.Payroll.Management.System.models.Employee;
import com.pms.Payroll.Management.System.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee-management")
//THIS CONTROLLER IS ACCESSIBLE TO HR ONLY
public class EmployeeRegistrationController {

  @Autowired
  private EmployeeService employeeService;

  @PostMapping("/employees")
  public ResponseEntity<?> registerAdministrativeStaff(@RequestBody @Valid EmployeeRegister employee){
    System.out.println("HEUHIUBVJODAVNOAJDV");
    return employeeService.addStaff(employee);

  }

  @GetMapping("employees/{employeeId}")
  public ResponseEntity<?> getEmployeeByEmployeeId(@PathVariable Long employeeId) {
    return employeeService.getEmployeeById(employeeId);
  }
}
