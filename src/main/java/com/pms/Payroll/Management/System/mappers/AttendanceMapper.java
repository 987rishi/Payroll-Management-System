package com.pms.Payroll.Management.System.mappers;

import com.pms.Payroll.Management.System.dto.AttendanceDto;
import com.pms.Payroll.Management.System.models.Attendance;
import com.pms.Payroll.Management.System.models.Employee;
import com.pms.Payroll.Management.System.repo.EmployeeRepo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {


//  @Mapping(source = "employee" ,target = "employee" ,qualifiedByName =
//          "idToEmployee")
//  Attendance toEntity(AttendanceDto attendanceDto);


  @Mapping(source = "attendance.employee.employeeId" ,target = "employee")
  AttendanceDto toDto(Attendance attendance);

//  @Named("idToEmployee")
//  default Employee idToEmployee(Long empId) {
//    if(empId == null) return null;
//
//    Employee employee = new Employee();
//    employee.setEmployeeId(empId);
//
//    return employee;
//  }
}
