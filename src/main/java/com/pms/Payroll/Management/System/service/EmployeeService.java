package com.pms.Payroll.Management.System.service;

import com.pms.Payroll.Management.System.dto.EmployeeRegister;
import com.pms.Payroll.Management.System.models.*;
import com.pms.Payroll.Management.System.repo.*;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.NoSuchElementException;


@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private AttendanceRepo attendanceRepo;


    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private BCryptPasswordEncoder encoder;


    private static  final Logger logger = LoggerFactory.getLogger(EmployeeService.class);


    @Transactional
    public ResponseEntity<?> addStaff(@Valid EmployeeRegister employee) {
        Employee employeeToSave = toEntity(employee);
        Attendance attendance = buildAttendance(employee,employeeToSave);

        AppUser user = new AppUser();
        user.setUserEmail(employee.getEmail());
        user.setPassword(encoder.encode(employee.getPassword()));

        Role role = roleRepo.findByRoleName(employee
                        .getRoleName())
                .orElseThrow(() ->
                        new IllegalArgumentException("GIVEN ROLE " +
                                "DOES NOT EXIST :" +
                                employee.getRoleName()));
        user.setRole(role);

        try {
            Employee res = employeeRepo.save(employeeToSave);
            attendanceRepo.save(attendance);
            appUserRepo.save(user);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("ERROR OCCURED WHILE SAVING EMPLOYEE:{}",e.getMessage(),e);
            throw new RuntimeException(e);
        }

    }

    private Attendance buildAttendance(@Valid EmployeeRegister employee, Employee employeeToSave) {
        Attendance attendance = new Attendance();
        attendance.setEmployee(employeeToSave);
        attendance.setMonth(LocalDate.now().getMonth());
        return attendance;
    }

    private Employee toEntity(EmployeeRegister employeeRegister){
        Employee employee = new Employee();
        Role role = roleRepo.findByRoleName(employeeRegister
                .getRoleName())
                .orElseThrow(() ->
                        new IllegalArgumentException("GIVEN ROLE " +
                        "DOES NOT EXIST :" +
                                employeeRegister.getRoleName()));

        Department department = departmentRepo
                .findByDeptName(employeeRegister.
                        getDepartmentName())
                .orElseThrow(() ->
                        new IllegalArgumentException("THE GIVEN " +
                        "DEPARTMENT DOES NOT EXIST : " +
                                employeeRegister.getDepartmentName()));



        employee.setEmployeeFname(employeeRegister.getEmployeeFname());
        employee.setEmployeeLname((employeeRegister.getEmployeeLname()));
        employee.setEmail(employeeRegister.getEmail());
        employee.setPhoneNumber(employeeRegister.getPhoneNumber());
        employee.setContractDurationStart(employeeRegister.getContractDurationStart());
        employee.setContractDurationEnd(employeeRegister.getContractDurationEnd());
        employee.setStartingDate(employeeRegister.getStartingDate());
        employee.setRole(role);
        employee.setDepartment(department);
        employee.setDesignation(employeeRegister.getDesignation());

        return employee;
    }

    public ResponseEntity<?> getEmployeeById(Long employeeId) {
        return new ResponseEntity<>(employeeRepo
                .findById(employeeId.intValue())
                .orElseThrow(() ->
                        new NoSuchElementException("EMPLOYEE DOES NOT EXIST " +
                                "CORRESPONDING TO ID:" + employeeId)),
                HttpStatus.OK
        );
    }
}
