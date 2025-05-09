package com.pms.Payroll.Management.System.service;

import com.pms.Payroll.Management.System.dto.AppealDto;
import com.pms.Payroll.Management.System.mappers.AppealMapper;
import com.pms.Payroll.Management.System.models.Appeals;
import com.pms.Payroll.Management.System.models.Employee;
import com.pms.Payroll.Management.System.repo.AppealRepo;
import com.pms.Payroll.Management.System.repo.EmployeeRepo;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class AppealService {

  @Autowired
  private AppealMapper appealMapper;

  @Autowired
  private AppealRepo appealRepo;

  @Autowired
  private EmployeeRepo employeeRepo;

  private static final Logger logger =
          LoggerFactory.getLogger(AppealService.class);

  @Transactional
  public ResponseEntity<?> createAppeal(@Valid AppealDto appealDto) {

    validateEmployeeNumbers(appealDto);

    Appeals appeal = appealMapper.toEntity(appealDto);
    logger.info("APPEAL: {}",appeal);

    try {
      return new ResponseEntity<>(
              appealRepo.save(appeal),
              HttpStatus.CREATED
      );
    } catch (Exception e) {
      logger.error("ERROR OCCURRED WHILE SAVING APPEAL",e);
      throw new RuntimeException(e);
    }
  }

  private void validateEmployeeNumbers(@Valid AppealDto appealDto) {
    Employee owningEmployee = employeeRepo
            .findById(appealDto.getEmployee())
            .orElseThrow(() ->
                    new NoSuchElementException(
                            "EMPLOYEE DOES NOT EXIST CORRESPONDING TO " +
                                    "EMPLOYEE ID: " + appealDto.getEmployee()
                    ));

    Employee toSendEmployee = employeeRepo
            .findById(appealDto.getSentToEmployee())
            .orElseThrow(() ->
                    new NoSuchElementException(
                            "EMPLOYEE DOES NOT EXIST CORRESPONDING TO " +
                                    "EMPLOYEE ID: " +
                                    appealDto.getSentToEmployee()
                    ));

    if(owningEmployee.getRole().getRank() > toSendEmployee.getRole().getRank())
      throw new IllegalArgumentException("EMPLOYEE SENDING HAS HIGHER " +
              "AUTHORITY THAN SEND TO EMPLOYEE");
  }

  public ResponseEntity<?> getAppealById(Integer appealId) {
    AppealDto appealDto = appealMapper.toDto(
            appealRepo
                    .findById(appealId)
                    .orElseThrow(() ->
                            new NoSuchElementException(
                                    "APPEAL DOES NOT EXIST CORRESPONDING TO " +
                                            "APPEAL ID:" + appealId
                            )
                    )
            );
    return new ResponseEntity<>(appealDto,HttpStatus.OK);
  }

  public ResponseEntity<?> getAllAppeals() {
    List<AppealDto> appealDtos = appealRepo
            .findAll()
            .stream()
            .map(appealMapper::toDto)
            .collect(Collectors.toList()
            );

    return new ResponseEntity<>(appealDtos,HttpStatus.OK);
  }
}
