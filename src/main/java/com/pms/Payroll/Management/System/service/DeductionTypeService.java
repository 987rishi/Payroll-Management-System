package com.pms.Payroll.Management.System.service;

import com.pms.Payroll.Management.System.dto.DeductionTypeDto;
import com.pms.Payroll.Management.System.mappers.DeductionTypeMapper;
import com.pms.Payroll.Management.System.models.DeductionType;
import com.pms.Payroll.Management.System.repo.DeductionTypeRepo;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class DeductionTypeService {

  @Autowired
  private DeductionTypeRepo deductionTypeRepo;

  @Autowired
  private DeductionTypeMapper deductionTypeMapper;

  private Logger logger = LoggerFactory.getLogger(DeductionTypeService.class);

  public ResponseEntity<?> createDeductionType(@Valid
                                               DeductionTypeDto
                                                       deductionTypeDto) {

    DeductionType deductionType =
            deductionTypeMapper
                    .toEntity(deductionTypeDto);

    logger.info("DEDUCTION TYPE {}",deductionType);

    try {
      return new ResponseEntity<>(
              deductionTypeRepo.save(deductionType),
                      HttpStatus.CREATED
      );
    } catch (Exception e) {
      logger.error("ERROR OCCURED WHILE SAVING DEDUCTION TYPE",e);
      throw new RuntimeException(e);
    }
  }

  public ResponseEntity<?> getDeductionTypeByName(@Valid String deductionTypeName) {

    DeductionTypeDto deductionTypeDto =
            deductionTypeMapper.toDto(deductionTypeRepo
                    .findByDeductionTypeName(deductionTypeName)
                    .orElseThrow(() ->
                            new NoSuchElementException("DEDUCTION TYPE DOES " +
                                    "NOT EXIST CORRESPONDING TO DEDCUTION " +
                                    "TYPE NAME:" + deductionTypeName))
            );
    return new ResponseEntity<>(deductionTypeDto, HttpStatus.OK);


  }

  public ResponseEntity<?> getAllDeductionTypes() {
    List<DeductionTypeDto> deductionTypeDtos = deductionTypeRepo
            .findAll()
            .stream()
            .map(deductionTypeMapper::toDto)
            .collect(Collectors.toList());

    return new ResponseEntity<>(deductionTypeDtos, HttpStatus.OK);


  }
}
