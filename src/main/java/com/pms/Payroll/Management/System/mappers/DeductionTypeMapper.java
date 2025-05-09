package com.pms.Payroll.Management.System.mappers;

import com.pms.Payroll.Management.System.dto.DeductionTypeDto;
import com.pms.Payroll.Management.System.models.DeductionType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DeductionTypeMapper {

  DeductionType toEntity(DeductionTypeDto deductionTypeDto);

  DeductionTypeDto toDto(DeductionType deductionType);
}
