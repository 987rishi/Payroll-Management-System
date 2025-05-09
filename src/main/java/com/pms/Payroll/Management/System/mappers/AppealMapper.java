package com.pms.Payroll.Management.System.mappers;

import com.pms.Payroll.Management.System.dto.AppealDto;
import com.pms.Payroll.Management.System.models.Appeals;
import com.pms.Payroll.Management.System.models.Employee;
import com.pms.Payroll.Management.System.repo.EmployeeRepo;
import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Mapper(componentModel = "spring")
public abstract class AppealMapper {

  @Autowired
  protected EmployeeRepo employeeRepository;

  @Mapping(source = "sentToEmployee", target = "sentToEmployee")
  @Mapping(source = "employee",       target = "employee")
  @Mapping(source = "approvedBy", target = "approvedBy")
//  @Mapping(target = "attachment", expression = "java(mapAttachment(appealDto.getAttachment()))")
  @Mapping(target = "attachment", source = "attachment")

  public abstract Appeals toEntity(AppealDto appealDto);

  @InheritInverseConfiguration
  @Mapping(target = "attachment", source = "attachment")
  @Mapping(source = "sentToEmployee.employeeId", target =
          "sentToEmployee")
  @Mapping(source = "employee.employeeId", target = "employee")
  @Mapping(source = "approvedBy.employeeId",target = "approvedBy")
  public abstract AppealDto toDto(Appeals appeals);
//  Appeals appeals = new Appeals().getSentToEmployee().g

  // helper for MultipartFile→byte[]
  protected byte[] mapAttachment(MultipartFile file) {
    try {
      return (file != null && !file.isEmpty()) ? file.getBytes() : null;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  // these are automatically used whenever you map Integer→Employee
  protected Employee mapSentToEmployee(Integer id) {
    return id == null
            ? null
            : employeeRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Employee not found: " + id));
  }

//  protected Employee mapEmployee(Integer id) {
//    return mapSentToEmployee(id);
//  }

//  protected Employee mapApprovedByEmployee(Integer id) {
//    return mapSentToEmployee(id);
//  }
}

