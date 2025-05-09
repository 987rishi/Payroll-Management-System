package com.pms.Payroll.Management.System.service;

import com.pms.Payroll.Management.System.models.Department;
import com.pms.Payroll.Management.System.repo.DeptRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class DeptService {

  @Autowired
  private DeptRepo deptRepo;

  public ResponseEntity<?> createDept(String deptName) {
    Department department = new Department(null, deptName);
    try{
      return new ResponseEntity<>(deptRepo.save(department),
              HttpStatus.CREATED);

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public ResponseEntity<?> getDeptByName(String deptName) {
    return new ResponseEntity<>(deptRepo
            .findByDeptName(deptName)
            .orElseThrow(() ->
                    new NoSuchElementException("DEPT DOES NOT EXIST " +
                            "CORRESPONDING TO DEPT NAME:" + deptName))
    ,HttpStatus.OK);
  }

  public ResponseEntity<?> getAllDept() {
    return new ResponseEntity<>(deptRepo.findAll(), HttpStatus.OK);
  }
}
