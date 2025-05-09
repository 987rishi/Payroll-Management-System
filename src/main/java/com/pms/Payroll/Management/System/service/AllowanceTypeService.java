package com.pms.Payroll.Management.System.service;


import com.pms.Payroll.Management.System.dto.AllowanceTypeDto;
import com.pms.Payroll.Management.System.mappers.AllowanceTypeMapper;
import com.pms.Payroll.Management.System.models.AllowanceType;
import com.pms.Payroll.Management.System.repo.AllowanceTypeRepo;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class AllowanceTypeService {


  @Autowired
  private AllowanceTypeMapper allowanceTypeMapper;

  @Autowired
  private AllowanceTypeRepo allowanceTypeRepo;


  @Autowired
  private static final Logger logger =
          LoggerFactory
                  .getLogger(
                          AllowanceTypeService.class
                  );

  public ResponseEntity<?> createAllowanceType(@Valid
                                               AllowanceTypeDto allowanceTypedto) {

    AllowanceType allowance = allowanceTypeMapper.toEntity(allowanceTypedto);

    logger.info("ALLOWANCE TYPE {}" , allowance);

    try{
      return new ResponseEntity<>(
              allowanceTypeRepo.save(allowance),
              HttpStatus.CREATED);
    } catch (Exception e) {
      logger.error("ERROR OCCURED WHILE SAVING ALLOWANCE TYPE",e);
      throw new RuntimeException(e);
    }
  }

  public ResponseEntity<?> getAllowanceTypeName(String allowanceName) {
    AllowanceTypeDto allowanceTypeDto = allowanceTypeMapper.
            toDto(allowanceTypeRepo
                    .findByAllowanceTypeName(allowanceName)
                    .orElseThrow(() ->
                            new NoSuchElementException(
                                    "ALLOWANCE TYPE DOES NOT EXIST CORRESPONDING " +
                                            "TO " +
                                            "ALLOWANCE TYPE NAME" +
                                            allowanceName
                            )
                    )
            );
    return new ResponseEntity<>(
            allowanceTypeDto,
            HttpStatus.OK
    );
  }

  public ResponseEntity<?> getAllAllowanceTypes() {
    List<AllowanceTypeDto> allowanceTypeDtos = allowanceTypeRepo
            .findAll()
            .stream()
            .map(allowanceTypeMapper::toDto)
            .collect(Collectors.toList());

    return new ResponseEntity<>(
            allowanceTypeDtos,
            HttpStatus.OK
    );
  }
}
