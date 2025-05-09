package com.pms.Payroll.Management.System.controllers;

import com.pms.Payroll.Management.System.dto.DeductionTypeDto;
import com.pms.Payroll.Management.System.service.DeductionTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deduction-type-management")
public class DeductionTypeController {

  @Autowired
  private DeductionTypeService deductionTypeService;

  @PostMapping("deduction-types")
  public ResponseEntity<?> createDeductionType(@RequestBody
                                               @Valid
                                               DeductionTypeDto deductionTypeDto) {
    return deductionTypeService.createDeductionType(deductionTypeDto);
  }

  @GetMapping("deduction-types/{deductionTypeName}")
  public ResponseEntity<?> getDeductionTypeByName(@PathVariable
                                               @Valid
                                               String deductionTypeName) {
    return deductionTypeService.getDeductionTypeByName(deductionTypeName);
  }

  @GetMapping("deduction-types")
  public ResponseEntity<?> getAllDeductionTypes() {
    return deductionTypeService.getAllDeductionTypes();
  }
}
