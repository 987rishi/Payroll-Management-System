package com.pms.Payroll.Management.System.mappers;

import com.pms.Payroll.Management.System.dto.AllowanceTypeDto;
import com.pms.Payroll.Management.System.models.AllowanceType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AllowanceTypeMapper {

  AllowanceType toEntity(AllowanceTypeDto allowanceTypeDto);

  AllowanceTypeDto toDto(AllowanceType allowanceType);

}
