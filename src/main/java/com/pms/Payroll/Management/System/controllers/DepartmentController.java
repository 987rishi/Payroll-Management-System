package com.pms.Payroll.Management.System.controllers;

import com.pms.Payroll.Management.System.models.Department;
import com.pms.Payroll.Management.System.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department-management")
public class DepartmentController {
  @Autowired
  private DeptService deptService;

  @PostMapping("departments")
  public ResponseEntity<?> createDept(@RequestBody Department dept) {
    return deptService.createDept(dept.getDeptName());
  }
  @GetMapping("departments/{deptName}")
  public ResponseEntity<?> getDepartmentByName(@PathVariable String deptName) {
    return deptService.getDeptByName(deptName);
  }

  @GetMapping("departments")
  public ResponseEntity<?> getAllDepartments() {
    return deptService.getAllDept();
  }
}
