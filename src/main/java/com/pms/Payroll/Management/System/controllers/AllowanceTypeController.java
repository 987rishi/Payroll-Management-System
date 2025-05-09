package com.pms.Payroll.Management.System.controllers;

import com.pms.Payroll.Management.System.dto.AllowanceTypeDto;
import com.pms.Payroll.Management.System.service.AllowanceTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/allowance-type-management")
public class AllowanceTypeController {

  @Autowired
  private AllowanceTypeService allowanceTypeService;

  @PostMapping("allowance-types")
  public ResponseEntity<?> createAllowanceType(@RequestBody @Valid
                                               AllowanceTypeDto allowanceTypeDto) {
    return allowanceTypeService.createAllowanceType(allowanceTypeDto);
  }

  @GetMapping("allowance-types/{allowanceName}")
  public ResponseEntity<?> getAllowanceTypeName(@PathVariable
                                                String allowanceName) {
    return allowanceTypeService.getAllowanceTypeName(allowanceName);
  }

  @GetMapping("allowance-types")
  public ResponseEntity<?> getAllAllowanceTypes() {
    return allowanceTypeService.getAllAllowanceTypes();
  }






}
