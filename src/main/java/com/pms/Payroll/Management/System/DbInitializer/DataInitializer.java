package com.pms.Payroll.Management.System.DbInitializer;

import com.pms.Payroll.Management.System.models.AllowanceType;
import com.pms.Payroll.Management.System.models.DeductionType;
import com.pms.Payroll.Management.System.models.Department;
import com.pms.Payroll.Management.System.models.Role;
import com.pms.Payroll.Management.System.repo.AllowanceTypeRepo;
import com.pms.Payroll.Management.System.repo.DeductionTypeRepo;
import com.pms.Payroll.Management.System.repo.DepartmentRepo;
import com.pms.Payroll.Management.System.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

  @Autowired
  private RoleRepo roleRepo;

  @Autowired
  private AllowanceTypeRepo allowanceTypeRepo;

  @Autowired
  private DeductionTypeRepo deductionTypeRepo;

  @Autowired
  private DepartmentRepo departmentRepo;

  @Override
  public void run(String... args) throws Exception {

    if(roleRepo.count() != 0) return;
    roleRepo.saveAll(List.of(
            new Role(null, "DEPT_HEAD",2),
            new Role(null, "HR",2),
            new Role(null, "FINANCE_MANAGER",3),
            new Role(null, "ADMINISTRATIVE_STAFF",1),
            new Role(null, "TEACHING_STAFF",1),
            new Role(null, "SUPPORT_STAFF",1)
    ));

    if(allowanceTypeRepo.count() != 0) return;
    allowanceTypeRepo.saveAll(
            List.of(
                    new AllowanceType(null, "DEARNESS_ALLOWANCE", 200.0),
                    new AllowanceType(null, "TRANSPORT_ALLOWANCE", 150.60),
                    new AllowanceType(null, "TRAVELLING_ALLOWANCE", 134.0),
                    new AllowanceType(null, "ENTERTAINMENT_ALLOWANCE", 245.0)
            )
    );

    if(deductionTypeRepo.count() != 0) return;
    deductionTypeRepo.saveAll(
            List.of(
                    new DeductionType(null, "STATE_INCOME_TAX", 100.0),
                    new DeductionType(null, "CENTRAL_INCOME_TAX", 200.0),
                    new DeductionType(null, "MEDICAL_INSURANCE", 50.0),
                    new DeductionType(null, "PROVIDENT_FUND", 75.0)

            )
    );

    if(departmentRepo.count() != 0) return;
    departmentRepo.saveAll(
            List.of(
                    new Department(null, "CSE"),
                    new Department(null, "ME"),
                    new Department(null, "ECE"),
                    new Department(null, "ADMINISTRATION")
            )
    );
  }
}
